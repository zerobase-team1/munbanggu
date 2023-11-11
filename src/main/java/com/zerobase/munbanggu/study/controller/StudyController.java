package com.zerobase.munbanggu.study.controller;

import com.zerobase.munbanggu.config.auth.TokenProvider;
import com.zerobase.munbanggu.study.dto.StudyDto;
import com.zerobase.munbanggu.study.model.entity.Study;
import com.zerobase.munbanggu.study.service.StudyService;
import com.zerobase.munbanggu.user.model.entity.User;
import com.zerobase.munbanggu.user.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final UserService userService;
    private final TokenProvider tokenProvider;
    @PostMapping()
    public ResponseEntity<Study> openStudy(@RequestHeader(name = "Authorization") String token,@RequestBody StudyDto studyDto) {

        Study openedStudy = studyService.openStudy(studyDto);
        return new ResponseEntity<>(openedStudy, HttpStatus.CREATED);
    }

    @PutMapping("{study_id}")
    public ResponseEntity<?> updateStudy(@PathVariable Long studyId,
            @RequestHeader(name = "Authorization") String token,
            @RequestBody StudyDto updatedStudyDto) {
        if (token == null) {
            // 'Authorization' 헤더가 누락된 경우 처리
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 존재하지 않음");
        }
        Optional<User> user = userService.getUser(tokenProvider.getId(token));
        if (user.isPresent()) {
            Study updatedStudy = studyService.updateStudy(studyId, updatedStudyDto);
            return new ResponseEntity<>(updatedStudy, HttpStatus.OK);
        }else {
            // 토큰이 유효하지 않은 경우 처리
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");
        }

    }

    @DeleteMapping("{study_id}")
    public ResponseEntity<String> deleteStudy(@PathVariable Long studyId,
            @RequestHeader(name = "Authorization") String token
    ) {
        if (token == null) {
            // 'Authorization' 헤더가 누락된 경우 처리
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 존재하지 않음");
        }
        Optional<User> user = userService.getUser(tokenProvider.getId(token));
        if (user.isPresent()) {
            studyService.deleteStudy(studyId);
            return ResponseEntity.ok("스터디 삭제가 완료되었습니다.");
        }else {
            // 토큰이 유효하지 않은 경우 처리
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");
        }

    }

}

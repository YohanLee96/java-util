package com.gsd.config.security.filter.error;

import com.blockware.gwonjidan.common.config.security.SecurityFilterUtil;
import com.blockware.gwonjidan.common.dto.ResponseDTO;
import com.blockware.gwonjidan.common.error.code.SecurityErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증된 유저의 권한으로 API에 접근이 불가능할 경우, 처리해주는 구현체(403 처리)
 */
@Slf4j
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {



    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException exception)
            throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        ResponseDTO errorResponse =
                new ResponseDTO(SecurityErrorCode.ACCESS_DENIED_MESSAGE);

        SecurityFilterUtil.jsonResponse(response, errorResponse);

    }
}

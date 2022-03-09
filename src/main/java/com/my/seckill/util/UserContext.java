package com.my.seckill.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.seckill.ATO.ResposeBeanCode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class UserContext {

    public void  render(HttpServletResponse httpServletResponse, ResposeBeanCode resposeBeanCode) throws IOException {

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(resposeBeanCode));
        writer.flush();
        writer.close();

    }

}

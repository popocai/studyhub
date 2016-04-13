package com.ericsson.http;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

/**
 * Servlet implementation class HttpBasicAuthertication
 */
public class HttpBasicAuthertication extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!checkHeaderAuth(request)) {
            response.setHeader("Cache-Control", "no-store");
            response.setDateHeader("Expires", 0);
            response.setHeader("WWW-authenticate", "Basic Realm=\"Ericsson\"");
            response.setStatus(401);
        } else {
            try {
                downloadFile(request, response);
            } catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private boolean checkHeaderAuth(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        System.out.println("auth encoded in base64 is " + auth);

        if ((auth != null) && (auth.length() > 6)) {
            auth = auth.substring(6, auth.length());

            String decodedAuth = base64Decode(auth);
            System.out.println("auth decoded from base64 is " + decodedAuth);

            if ("ericsson:Ericss0n".equals(decodedAuth)) {
                request.getSession().setAttribute("auth", decodedAuth);
                return true;
            }
        }
        return false;

    }

    private String base64Decode(String s) {
        if (s == null) {
            return null;
        }

        Base64 decoder = new Base64();
        byte[] bytes = decoder.decode(s.getBytes());
        return new String(bytes);
    }

    public void downloadFile(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ServletContext context = getServletConfig().getServletContext();
        List<String> fileList = new ArrayList<String>();

        String download = "/download";
        String path = context.getRealPath(download);
        System.out.println("Path : " + path);

        File downloadDir = new File(path);

        if (downloadDir.isDirectory()) {
            File[] files = downloadDir.listFiles();

            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file.getName());
                    System.out.println(file.getName());
                }
            }
            context.setAttribute("files", fileList);
        }
        request.getRequestDispatcher("WEB-INF/list.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}

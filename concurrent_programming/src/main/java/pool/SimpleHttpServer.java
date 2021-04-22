package pool;

import pool.impl.ThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//import java.net.http.HttpRequest;

public class SimpleHttpServer {

    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>();
    // SimpleHttpServer
    static String basePath;
    static ServerSocket serverSocket;
    //服务监听端口
    static int port = 8080;

    public static void setPort(int port){
        if (port > 0){
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath){
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    //启动SimpleHttpServer
    public static void start() throws Exception{
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null){
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();

    }


    static class HttpRequestHandler implements Runnable{

        private Socket socket;

        public HttpRequestHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;

            PrintWriter out = null;
            InputStream in  = null;

            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                //由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                //如果请求资源后缀名为jps或者ico,则读取资源并输出
                if (filePath.endsWith("jpg") || filePath.endsWith("ico")){
                    File file;
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i=in.read()) != -1){
                        baos.write(i);
                    }

                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    socket.getOutputStream().write(array, 0, array.length);
                }else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    while ((line = br.readLine()) != null) {
                        out.println(line);
                    }
                }
                out.flush();
            } catch (IOException e) {
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
                e.printStackTrace();
            }


        }


        // 关闭流或者Socket
        private static void close(Closeable... closeables) {
            if (closeables != null) {
                for (Closeable closeable : closeables) {
                    try {
                        closeable.close();
                    } catch (Exception ex) {
                        // 忽略
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        basePath = "/Users/backbook/IdeaProjects/java_internal_skill_training/concurrent_programming/src/main/resources/";
        SimpleHttpServer.setBasePath(basePath);
        try{
            start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}

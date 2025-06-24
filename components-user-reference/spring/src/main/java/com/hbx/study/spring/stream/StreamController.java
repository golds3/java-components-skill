package com.hbx.study.spring.stream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.Executors;

/**
 * 流式输出，现在AI对话基本都是流
 *
 */
@Controller
public class StreamController {
    /**
     * 方式一：ResponseEntity + StreamResponseBody
     * 同步的方式，占据连接
     * 不需要body构建完才响应给客户端，边构建边响应
     */
    @GetMapping("/stream1")
    public ResponseEntity<StreamingResponseBody> chat(){
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_EVENT_STREAM_VALUE).body(outputStream -> {
            for (int i = 0; i < 100; i++) {
                String data = "data chunk "+ i + "\n";
                outputStream.write(data.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        });
    }


    /**
     * 方式二：SSE Event 服务器推送事件，单向通信，服务端主动推送给客户端
     */
    @GetMapping("/stream2")
    public SseEmitter chat2(){
        SseEmitter sseEmitter = new SseEmitter(600_00L);
        Executors.newSingleThreadScheduledExecutor().submit(() -> {
            for (int i = 0; i < 100; i++) {
                String data = "data chunk "+ i + "\n";
                try {
                    sseEmitter.send(data);
                    Thread.sleep(500);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            sseEmitter.complete();
        });
        return sseEmitter;
    }

    /**
     * 方式三：使用Flux 推荐
     * 响应式编程，是异步非阻塞的
     */
    @GetMapping("/stream3")
    public Flux<String> chat3(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> "data chunk " + i + "\n")
                .take(100);
    }

}

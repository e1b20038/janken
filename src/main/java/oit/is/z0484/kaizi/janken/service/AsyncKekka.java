package oit.is.z0484.kaizi.janken.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class AsyncKekka {
  int count = 1;
  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Async
  public void count(SseEmitter emitter) throws IOException {
    logger.info("count start");
    try {
      while (true) {
        logger.info("send;" + count);
        emitter.send(count);
        count++;
        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {
logger.warn("Exception:" + e.getMessage());
    }
  }
}

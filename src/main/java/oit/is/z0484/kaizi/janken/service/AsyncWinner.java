package oit.is.z0484.kaizi.janken.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z0484.kaizi.janken.model.Janken;
import oit.is.z0484.kaizi.janken.model.Match;
import oit.is.z0484.kaizi.janken.model.MatchInfoMapper;
import oit.is.z0484.kaizi.janken.model.MatchMapper;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AsyncWinner {
  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Autowired
  MatchMapper mmapper;

  @Autowired
  MatchInfoMapper mimapper;

  @Async
  public void winner(SseEmitter emitter) throws IOException {
    logger.info("Async start");
    Match ActiveMatch = mmapper.selectActiveMatch();

    try {
      if (ActiveMatch.getIsActive() == true) {
        String winner = Janken.match(ActiveMatch);
        emitter.send(winner);
        mimapper.updateMatchInfoF();
      }

    } catch (Exception e) {
      logger.warn("Exception:" + e.getMessage());
    } finally {
      emitter.complete();

    }
  }
}

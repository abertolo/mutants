package com.example.mutants.service.impl;

import com.example.mutants.controller.MutantsController;
import com.example.mutants.model.db.DnaSample;
import com.example.mutants.model.db.DnaSampleDao;
import com.example.mutants.model.response.MutantsStatsResponse;
import com.example.mutants.service.MutantsDetector;
import com.example.mutants.service.MutantsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mnsantos on 10/7/17.
 */

@Service("MutantsServiceImpl")
public class MutantsServiceImpl implements MutantsService {

    @Resource(name = "MutantsDetectorImpl")
    private MutantsDetector mutantsDetector;
    @Autowired
    private DnaSampleDao dnaSampleDao;
    private final static Logger logger = LoggerFactory.getLogger(MutantsController.class);

    @Override
    public boolean isMutant(List<String> dna) {
        String dnaChain = asString(dna);
        DnaSample dnaSample = dnaSampleDao.findByDna(dnaChain);
        if (dnaSample != null) {
            logger.info(String.format("Retrieving %s from database", dnaSample));
            return dnaSample.isMutant();
        } else {
            boolean isMutant = mutantsDetector.isMutant(dna);
            dnaSample = new DnaSample(dnaChain, isMutant);
            logger.info(String.format("Saving %s", dnaSample));
            dnaSampleDao.save(dnaSample);
            return isMutant;
        }
    }

    private String asString(List<String> dna) {
        return String.join(",", dna);
    }

    @Override
    public MutantsStatsResponse stats() {
        long totalSamples = dnaSampleDao.count();
        long totalMutant = dnaSampleDao.countByMutant(true);
        Double ratio = 0.0;
        if (totalSamples > 0) {
            ratio = (double) totalMutant / totalSamples;
        }
        return new MutantsStatsResponse(totalMutant, totalSamples - totalMutant, ratio);
    }
}

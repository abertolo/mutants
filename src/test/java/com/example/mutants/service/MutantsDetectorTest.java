package com.example.mutants.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mnsantos on 10/8/17.
 */
public abstract class MutantsDetectorTest {

    private MutantsDetector mutantsDetector;

    protected abstract MutantsDetector createInstance();

    @Before
    public void setUp() {
        mutantsDetector = createInstance();
    }

    @Test
    public void test01EmptyDnaIsHuman() {
        Assert.assertFalse(mutantsDetector.isMutant(new ArrayList<>()));
    }

    /*
    AAAA
    TTTT
    CGCG
    CGCG
    */
    @Test
    public void test02horizontalMutantSequence() {
        Assert.assertTrue(mutantsDetector.isMutant(Arrays.asList("AAAA", "TTTT", "CGCG", "CGCG")));
    }

    /*
    AAAC
    TTTT
    CGCG
    CGCG
    */
    @Test
    public void test03horizontalHumanSequence() {
        Assert.assertFalse(mutantsDetector.isMutant(Arrays.asList("AAAC", "TTTT", "CGCG", "CGCG")));
    }

    /*
    CTCA
    GTGA
    CTCA
    GTGA
    */
    @Test
    public void test04VerticalMutantSequence() {
        Assert.assertTrue(mutantsDetector.isMutant(Arrays.asList("CTCA", "GTGA", "CTCA", "GTGA")));
    }

    /*
    CTCA
    GTGA
    CTCA
    GTGT
    */
    @Test
    public void test05VerticalHumanSequence() {
        Assert.assertFalse(mutantsDetector.isMutant(Arrays.asList("CTCA", "GTGA", "CTCA", "GTGT")));
    }

    /*
    ATCCG
    CATGC
    ACATC
    CTGAT
    CTGAT
    */
    @Test
    public void test06ObliqueRightMutantSequence() {
        Assert.assertTrue(mutantsDetector.isMutant(Arrays.asList("ATCCG", "CATGC", "ACATC", "CTGAT", "CTGAT")));
    }

    /*
    ATCCG
    CATGC
    ACATC
    CTGGT
    CTGAT
    */
    @Test
    public void test07ObliqueRightHumanSequence() {
        Assert.assertFalse(mutantsDetector.isMutant(Arrays.asList("ATCCG", "CACGC", "ACATC", "CTGGT", "CTGAT")));
    }

    /*
    ATCCG
    CGTGC
    ACGCC
    CGCCT
    CCGAT
    */
    @Test
    public void test08ObliqueLeftMutantSequence() {
        Assert.assertTrue(mutantsDetector.isMutant(Arrays.asList("ATCCG", "CGTGC", "ACGCC", "CGCCT", "CCGAT")));
    }

    /*
    ATCCG
    CGTGC
    ACGCC
    CGCCT
    CTGAT
    */
    @Test
    public void test09ObliqueLeftHumanSequence() {
        Assert.assertFalse(mutantsDetector.isMutant(Arrays.asList("ATCCG", "CGTGC", "ACGCC", "CGCCT", "CTGAT")));
    }
}

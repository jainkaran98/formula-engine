/*
 * Copyright, 1999-2018, salesforce.com
 * All Rights Reserved
 * Company Confidential
 */
package com.force.formula.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import org.xml.sax.SAXException;

import com.force.formula.FormulaEngine;
import com.force.formula.impl.BaseFieldReferenceTest.FieldTestFormulaValidationHooks;

import junit.framework.TestSuite;

/**
 * Contains non-extended tests for formulatests.xml
 * @author stamm
 * @since 212
 */
@RunWith(AllTests.class)
public class TestStandardFormulas extends FormulaGenericTests {

    public TestStandardFormulas(String owner) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
        super("StandardFormulaTests");
    }

    public static TestSuite suite()
    {
        try {
            return new TestStandardFormulas("no");
        } catch (ParserConfigurationException | SAXException | IOException x) {
            throw new RuntimeException(x);
        }
    }

    @Override
    protected boolean filterTests(FormulaTestCaseInfo testCase) {
        if (testCase.getTestLabels().contains("ignore")) return false;
        return testCase.getTestLabels().size() == 0 || testCase.getTestLabels().contains("basic");
    }

    @Override
    protected void setUpTest(BaseFormulaGenericTest test) {
        FormulaEngine.setHooks(new FieldTestFormulaValidationHooks());
        FormulaEngine.setFactory(BaseFieldReferenceTest.TEST_FACTORY);
    }

    
}
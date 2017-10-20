package com.sample.resources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.conf.SampleApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SampleApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SampleResourceTest {

  private SampleResource sampleResource;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    sampleResource = new SampleResource();
  }

  @Test
  public void dummyTest() {
    try {
      throw new RuntimeException("sdfsa");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}

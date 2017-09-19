package com.lidl.sourcecode;

import com.lidl.sourcecode.bean.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SourceCodeApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testLoad() throws IOException {
		Resource resource = new ClassPathResource("spring.xml");
		BeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
		TestBean testBean = xmlBeanFactory.getBean("testBean", TestBean.class);
		System.out.println(">> " + testBean.getName());
	}

}

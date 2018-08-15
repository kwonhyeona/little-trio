package kr.or.hanium.probono.littletrio.b4showing.domain;

import kr.or.hanium.probono.littletrio.b4showing.B4showingApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(B4showingApplication.class);
	}

}

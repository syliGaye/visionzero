package ci.dcg.visionzero;

import ci.dcg.visionzero.files.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class VisionzeroUnApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisionzeroUnApplication.class, args);
	}

}

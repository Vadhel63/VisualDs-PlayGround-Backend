package DsPlateform.Visualization;

import DsPlateform.Visualization.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class VisualizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisualizationApplication.class, args);
	}

}

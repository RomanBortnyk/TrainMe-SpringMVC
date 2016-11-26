package trainMe.aspects;

import org.springframework.stereotype.Component;

/**
 * Created by romab on 11/26/16.
 */
@Component
public class OrchestraConcert implements Performance {

    public String perform() {
        return "drin drin";
    }
}

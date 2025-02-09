package Engine.Components;
import java.lang.reflect.Field;

public class Script extends Component {
    @Override
    public Script clone() {
        // I generated this with AI, and dont understand exactly how it works.

        try {
            
            Script newScript = this.getClass().getDeclaredConstructor().newInstance();
            for (Field field : this.getClass().getDeclaredFields()) {
                field.setAccessible(true); 
                field.set(newScript, field.get(this)); 
            }
            return newScript;
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to clone Script: " + this.getClass().getSimpleName(), e);
        }
    }
}

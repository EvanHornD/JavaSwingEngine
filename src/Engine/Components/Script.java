package Engine.Components;
import java.lang.reflect.Field;

public class Script extends Component {
    @Override
    public Script clone() {
        try {
            // creates a new instance of the script
            Script newScript = this.getClass().getDeclaredConstructor().newInstance();

            // copies the values of all the fields to the new script
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

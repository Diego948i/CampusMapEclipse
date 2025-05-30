package campus.objects;

import javafx.scene.control.ScrollBar;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DormitoryRectangle {
	private int value;
	private Rectangle rectDorm;
	private Text textDorm;
	private ScrollBar scrollDorm;
	
	public Text getTextDorm() {
		return textDorm;
	}
	public void setTextDorm(Text textDorm) {
		this.textDorm = textDorm;
	}
	public Rectangle getRectDorm() {
		return rectDorm;
	}
	public void setRectDorm(Rectangle rectDorm) {
		this.rectDorm = rectDorm;
	}
	public ScrollBar getScrollDorm() {
		return scrollDorm;
	}
	public void setScrollDorm(ScrollBar scrollDorm) {
		this.scrollDorm = scrollDorm;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}

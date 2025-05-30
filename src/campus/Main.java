package campus;
	


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import campus.objects.DormitoryRectangle;
import campus.objects.ObjRect;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;



import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	
	private static Image imageBackground;
	private static ImageView imageViewBackground;
	private static BorderPane root = new BorderPane();
	
	private static List<Rectangle> listRectangle = new ArrayList<Rectangle>();
	private static List<Text> listText= new ArrayList<Text>();
	private static List<ScrollBar> listScroll= new ArrayList<ScrollBar>();
	
	

	private static ObjRect midRect, midFriendRect;
	
	private static List<DormitoryRectangle> listDorm = new ArrayList<DormitoryRectangle>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Scene scene = new Scene(root,720,480);
			
			imageBackground = new Image(getClass().getResource("images/campus.png").toString());
			
			inicializateVariables();

			for (DormitoryRectangle actualDorm : listDorm) {actionOnDorm(actualDorm);}
			
			addElementsOnScreen();
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Campus");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void inicializateVariables() {
		createBackground();
		createAndSetupDorms();
		setupViewsLists();
		
		Rectangle midRect = new Rectangle();
		Rectangle midFriendRect = new Rectangle();
		
		midRect = midCalculate(midRect, true);
		midRect = setupMidRect(midRect, 0, 104, 209);
		
		midFriendRect = midCalculate(midFriendRect, false);
		midFriendRect = setupMidRect(midFriendRect, 110, 209, 209);
		
		Main.midRect = createObjRect(midRect, createMidText("Média dos estudantes", midRect));
		Main.midFriendRect = createObjRect(midFriendRect, createMidText("Média dos amigos", midFriendRect));

	}
	
	private static ObjRect createObjRect(Rectangle midRect2, Text midText) {
		ObjRect o = new ObjRect();
		o.setMidRect(midRect2);
		o.setMidText(midText);
		return o;
	}

	private static void addElementsOnScreen() {
		root.getChildren().addAll(listRectangle);
		root.getChildren().addAll(listText);
		root.getChildren().addAll(listScroll);
		root.getChildren().add(midRect.getMidRect());
		root.getChildren().add(midFriendRect.getMidRect());

		root.getChildren().add(midRect.getMidText());
		root.getChildren().add(midFriendRect.getMidText());

	}
	
	private static void createBackground() {
		imageViewBackground = new ImageView(imageBackground);
		imageViewBackground.setLayoutX(0);
		imageViewBackground.setLayoutY(0);
		root.getChildren().add(imageViewBackground);
	}

	private static DormitoryRectangle createDormitorySquare() {
		
		Rectangle r = new Rectangle();
		
		Random rnd = new Random();
		
		r.setLayoutX(rnd.nextInt(620));
		r.setLayoutY(rnd.nextInt(380));
		r.setWidth(100);
		r.setHeight(100);
		r.setFill(Color.rgb(118, 104, 209, 0.5));
		r.setStroke(Color.rgb(75, 65, 130, 0.5));
		r.setStrokeWidth(2.0);
		
		DormitoryRectangle dm = new DormitoryRectangle();
		
		dm.setRectDorm(r);
		dm.setTextDorm(createDormRectText(r));
		dm.setScrollDorm(createDormRectScroll(r));
		
		dm.setValue(100);
		
		return dm;
	}
	
	private static Text createDormRectText(Rectangle rect) {
		
		Text text = new Text("");
		
		text.setFont(Font.font("Roboto Blacak", FontWeight.BOLD, 8));
		
		text.setLayoutX(rect.getLayoutX());
		text.setLayoutY(rect.getLayoutY() + rect.getHeight() + 10);
		
		return text;
		
	}
	
	private static ScrollBar createDormRectScroll(Rectangle rect) {
		
		ScrollBar sb = new ScrollBar();
		
		Random rnd = new Random();
		
		sb.setMin(100);
		sb.setMax(1000);
		sb.setValue(rnd.nextInt(999) + 1);
		sb.setMinSize(25, rect.getHeight());
		sb.setLayoutX(rect.getLayoutX() + rect.getWidth() + 10);
		sb.setLayoutY(rect.getLayoutY());
		sb.setOrientation(Orientation.VERTICAL);
		
		return sb;
		
	}
	
	private static Rectangle midCalculate(Rectangle rect, boolean isStudentsMid) {
		
		double totalX = 0, totalY = 0;
		int population = 0, size = 0;
		
		if (isStudentsMid) {size = 5;} else {size=3;}
		
		for (int i = 0; i < size; i++) {
			DormitoryRectangle actualDorm = listDorm.get(i);
			Rectangle actualRect = actualDorm.getRectDorm();
			
			if (isStudentsMid) {
			
				population += actualDorm.getValue();
			
				totalX += actualDorm.getValue() * (actualRect.getLayoutX() + (actualRect.getWidth()/2));
				totalY += actualDorm.getValue() * (actualRect.getLayoutY() + (actualRect.getHeight()/2));
			} else {
				population++;
				
				totalX += (actualRect.getLayoutX() + (actualRect.getWidth()/2));
				totalY += (actualRect.getLayoutY() + (actualRect.getHeight()/2));
			}
			
			rect.setLayoutX(totalX/population);
			rect.setLayoutY(totalY/population);
			
		}
		return rect;
	}

	private static Rectangle setupMidRect(Rectangle rect, int r, int g, int b) {
		rect.setWidth(20);
		rect.setHeight(20);
		rect.setFill(Color.rgb(r, g, b, 1));
		rect.setStroke(Color.rgb(r, (g-45), (b-71), 1));
		rect.setStrokeWidth(2.0);
		
		return rect;
	}
	
	private static void createAndSetupDorms() {
		for (int i = 0; i<5; i++) {
			listDorm.add(createDormitorySquare());
		}
	}
	
	private static DormitoryRectangle actionOnDorm(DormitoryRectangle actualDorm) {
		Rectangle rectActual = actualDorm.getRectDorm();
		Text textActual = actualDorm.getTextDorm();
		ScrollBar scrollActual = actualDorm.getScrollDorm();
		
		rectActual.setOnMouseDragged((MouseEvent me) -> {
			
			rectActual.setLayoutX(me.getSceneX() - rectActual.getWidth()/2);
			rectActual.setLayoutY(me.getSceneY() - rectActual.getHeight()/2);
			
			textActual.setLayoutX(rectActual.getLayoutX());
			textActual.setLayoutY(rectActual.getLayoutY() + rectActual.getHeight() + 10);
			
			scrollActual.setLayoutX(rectActual.getLayoutX() + rectActual.getWidth() + 10);
			scrollActual.setLayoutY(rectActual.getLayoutY());
			
			midRect.setMidRect(midCalculate(Main.midRect.getMidRect(), true));
			midFriendRect.setMidRect(midCalculate(Main.midFriendRect.getMidRect(), false));
			
			midRect.setMidText(moveText(midRect));
			midFriendRect.setMidText(moveText(midFriendRect));
			
		});
		
		scrollActual.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				
				actualDorm.setValue(arg2.intValue());
				textActual.setText("População de " + String.valueOf(arg2.intValue()) + " pessoas");
				
				Main.midRect.setMidRect(midCalculate(Main.midRect.getMidRect(), true));
				Main.midFriendRect.setMidRect(midCalculate(Main.midFriendRect.getMidRect(), false));
				
				midRect.setMidText(moveText(midRect));
				
			}
		});
		
		return actualDorm;
		
	}
	
	private static Text createMidText(String msg, Rectangle rect) {
		Text text = new Text(msg);
		
		text.setFont(Font.font("Roboto Blacak", FontWeight.BOLD, 8));
		
		text.setLayoutX(rect.getLayoutX());
		text.setLayoutY(rect.getLayoutY() + rect.getHeight() + 10);
		
		return text;
	}
	
	private static Text moveText(ObjRect obj) {
		Text tx = obj.getMidText();
		tx.setLayoutX(obj.getMidRect().getLayoutX());
		tx.setLayoutY(obj.getMidRect().getLayoutY() + obj.getMidRect().getHeight() + 10);
		return tx;
	}
	
	private static void setupViewsLists() {
		for (int i = 0; i<5; i++) {
			listRectangle.add(listDorm.get(i).getRectDorm());
			Text tx = listDorm.get(i).getTextDorm();
			DecimalFormat df = new DecimalFormat("###");
			tx.setText("População de " + df.format(listDorm.get(i).getScrollDorm().getValue()) + " pessoas");
			listText.add(tx);
			listScroll.add(listDorm.get(i).getScrollDorm());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
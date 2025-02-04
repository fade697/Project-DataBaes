package application;

import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main extends Application {
	private ArrayList<Anime> Adata;
	private ObservableList<Anime> AdataList;
	private ArrayList<Artist> Ardata;
	private ObservableList<Artist> ArdataList;
	private ArrayList<scene> sdata;
	private ObservableList<scene> sdataList;
	private ArrayList<VoiceActor> vcdata;
	private ObservableList<VoiceActor> vcdataList;
	private ArrayList<Writer> Wdata;
	private ObservableList<Writer> WdataList;
	private ArrayList<voice> vdata;
	private ObservableList<voice> vdataList;
	TableView<Writer> Writertable = new TableView<Writer>();
	TableView<scene> Scenetable = new TableView<scene>();
	TableView<Anime> Animetable = new TableView<Anime>();
	TableView<VoiceActor> VoiceActortable = new TableView<VoiceActor>();
	TableView<Artist> Artisttable = new TableView<Artist>();
	TableView<voice> voicetable = new TableView<voice>();

	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "fadeamlehvic1";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "sys";
	private SQLCursor con = new SQLCursor();

	@Override

	public void start(Stage primaryStage) {
		mainPage(primaryStage);
	}

	public void mainPage(Stage primaryStage) {
		// primaryStage.setMaximized(true);
		primaryStage.setTitle("Main Page");
		// this is for the list that have writer, media and rent

		HBox firstList = new HBox(10);
		// firstList.setAlignment(Pos.TOP_LEFT);
		// first button and Icon

		ImageView writerButton = new ImageView("https://cdn-icons-png.flaticon.com/512/9519/9519901.png");
		writerButton.setFitHeight(30);
		writerButton.setFitWidth(30);
		Button writer = new Button("Writers", writerButton);
		writer.setFont(Font.font("Times New Roman"));
		writer.setGraphic(writerButton);
		writer.setPrefSize(120, 30);
		writer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Writer(primaryStage);
			}
		});
		// second button and Icon
		// https://www.flaticon.com/free-icon/clapperboard_4096099?term=scene&page=1&position=47&origin=search&related_id=4096099
		ImageView voiceButton = new ImageView("https://cdn-icons-png.flaticon.com/512/1599/1599427.png");
		voiceButton.setFitHeight(30);
		voiceButton.setFitWidth(30);
		Button voiceActor = new Button("Voice Actors", voiceButton);
		voiceActor.setFont(Font.font("Times New Roman"));
		voiceActor.setPrefSize(120, 30);
		voiceActor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				VC(primaryStage);
			}
		});
		ImageView SceneButton = new ImageView("https://cdn-icons-png.flaticon.com/512/4059/4059990.png");
		SceneButton.setFitHeight(30);
		SceneButton.setFitWidth(30);
		Button Scene = new Button("Scenes", SceneButton);
		Scene.setFont(Font.font("Times New Roman"));
		Scene.setGraphic(SceneButton);
		Scene.setPrefSize(120, 30);
		Scene.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Scene(primaryStage);
			}

		});
		// third button and Icon
		ImageView AnimeButton = new ImageView("https://cdn-icons-png.flaticon.com/512/8669/8669814.png");
		AnimeButton.setFitHeight(30);
		AnimeButton.setFitWidth(30);
		Button Anime = new Button("Anime", AnimeButton);
		Anime.setFont(Font.font("Times New Roman"));
		Anime.setGraphic(AnimeButton);
		Anime.setPrefSize(120, 30);
		Anime.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AnimeAdd(primaryStage);
			}
		});
		ImageView artistButton = new ImageView("https://cdn-icons-png.flaticon.com/512/9430/9430131.png");
		artistButton.setFitHeight(30);
		artistButton.setFitWidth(30);
		Button Artist = new Button("Artists", artistButton);
		Artist.setFont(Font.font("Times New Roman"));
		Artist.setPrefSize(120, 30);
		Artist.setOnAction(e -> {
			Ardata = new ArrayList<>();
			try {
				ArtistgetData();
				ArtisttableView(primaryStage);

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ArdataList = FXCollections.observableArrayList(Ardata);

			Artist(primaryStage);

		});
		Button voice = new Button("voice", writerButton);
		voice.setFont(Font.font("Times New Roman"));
		voice.setGraphic(writerButton);
		voice.setPrefSize(120, 30);
		voice.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Voice(primaryStage);
			}
		});

		// adding them to the vbox
		VBox v = new VBox(25);
		firstList.getChildren().addAll(Anime, Scene, writer, voiceActor, Artist, voice);
		firstList.setAlignment(Pos.BOTTOM_CENTER);
		Image m = new Image("https://animegalaxyofficial.com/wp-content/uploads/2022/03/png_20220301_223440_0000.png");
		ImageView frontimag = new ImageView(m);
		frontimag.setFitHeight(600);
		frontimag.setFitWidth(800);
		Label rentsys = new Label("Rental Media System");
		rentsys.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
		v.getChildren().addAll(rentsys, frontimag, firstList);
		v.setAlignment(Pos.CENTER);
		BorderPane bp = new BorderPane();
		bp.setCenter(v);
		Scene s = new Scene(bp, 1200, 900, Color.BEIGE);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	private void Voice(Stage primaryStage) {
		primaryStage.setMaximized(true);
		vdata = new ArrayList<>();

		try {
			voicegetData();
			vdataList = FXCollections.observableArrayList(vdata);
			voicetableView(primaryStage);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		primaryStage.setTitle("voice");
		BorderPane p = new BorderPane();
		VBox b = new VBox(20);
		b.setPadding(new Insets(20, 20, 20, 20));
		VBox cusList = new VBox(10);
		VBox cusList2 = new VBox(10);
		HBox cusList3 = new HBox(30);
		Label Title = new Label("voice table:");
		Title.setFont(Font.font("Times New Roman", 20));
		Button returnto = new Button("Return");
		returnto.setFont(Font.font("Times New Roman", 15));
		returnto.setPrefSize(150, 30);
		returnto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				mainPage(primaryStage);
			}
		});
		cusList3.getChildren().addAll(returnto);

		b.getChildren().addAll(Title, voicetable, cusList3);
		p.setCenter(b);
		Scene c = new Scene(p, 1200, 900, Color.BEIGE);
		primaryStage.setScene(c);
		primaryStage.show();

	}

	private void Scene(Stage primaryStage) {
		primaryStage.setMaximized(true);
		sdata = new ArrayList<>();

		try {
			ScenegetData();
			sdataList = FXCollections.observableArrayList(sdata);
			ScenetableView(primaryStage);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		primaryStage.setTitle("Scene");
		BorderPane p = new BorderPane();
		VBox b = new VBox(20);
		b.setPadding(new Insets(20, 20, 20, 20));
		VBox cusList = new VBox(10);
		VBox cusList2 = new VBox(10);
		HBox cusList3 = new HBox(30);
		Label Title = new Label("Scene table:");
		Title.setFont(Font.font("Times New Roman", 20));
		Title.setPrefSize(80, 50);
		Button addWriter = new Button("Add scene");
		addWriter.setFont(Font.font("Times New Roman", 15));
		addWriter.setPrefSize(150, 30);
		addWriter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AddScene(primaryStage);
			}
		});
		Button deletewriter = new Button("Delete scene");
		deletewriter.setFont(Font.font("Times New Roman", 15));
		deletewriter.setPrefSize(150, 30);
		deletewriter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				deleteScene(primaryStage);
			}
		});
		Button updateInfo = new Button("Update Info");
		updateInfo.setFont(Font.font("Times New Roman", 15));
		updateInfo.setPrefSize(150, 30);
		updateInfo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateinfScene(primaryStage);
			}
		});
		Button search = new Button("Search");
		search.setPrefSize(150, 30);
		search.setFont(Font.font("Times New Roman", 15));
		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				searchScene(primaryStage);
			}
		});
		Button returnto = new Button("Return");
		returnto.setFont(Font.font("Times New Roman", 15));
		returnto.setPrefSize(150, 30);
		returnto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				mainPage(primaryStage);
			}
		});
		cusList.getChildren().addAll(addWriter, deletewriter);
		cusList2.getChildren().addAll(updateInfo, search);
		cusList3.getChildren().addAll(cusList, cusList2, returnto);
		b.getChildren().addAll(Title, Scenetable, cusList3);
		p.setCenter(b);
		Scene c = new Scene(p, Color.BEIGE);
		primaryStage.setScene(c);
		primaryStage.show();
	}

	private void VC(Stage primaryStage) {
		primaryStage.setMaximized(true);
		vcdata = new ArrayList<>();

		try {
			vcgetData();
			vcdataList = FXCollections.observableArrayList(vcdata);
			VoiceActortableView(primaryStage);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		primaryStage.setTitle("Voice actor");
		BorderPane p = new BorderPane();
		VBox b = new VBox(20);
		b.setPadding(new Insets(20, 20, 20, 20));
		VBox cusList = new VBox(10);
		VBox cusList2 = new VBox(10);
		HBox cusList3 = new HBox(30);
		Label Title = new Label("Voice actor table:");
		Title.setFont(Font.font("Times New Roman", 20));
		// Title.setPrefSize(80, 50);
		Button addWriter = new Button("Add voice actor");
		addWriter.setFont(Font.font("Times New Roman", 15));
		addWriter.setPrefSize(150, 30);
		addWriter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AddVC(primaryStage);
			}
		});
		Button deletewriter = new Button("Delete scene");
		deletewriter.setFont(Font.font("Times New Roman", 15));
		deletewriter.setPrefSize(150, 30);
		deletewriter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				deleteVC(primaryStage);
			}
		});
		Button updateInfo = new Button("Update Info");
		updateInfo.setFont(Font.font("Times New Roman", 15));
		updateInfo.setPrefSize(150, 30);
		updateInfo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateinfVC(primaryStage);
			}
		});
		Button search = new Button("Search");
		search.setPrefSize(150, 30);
		search.setFont(Font.font("Times New Roman", 15));
		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				searchVC(primaryStage);
			}
		});
		Button returnto = new Button("Return");
		returnto.setFont(Font.font("Times New Roman", 15));
		returnto.setPrefSize(150, 30);
		returnto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				mainPage(primaryStage);
			}
		});
		cusList.getChildren().addAll(addWriter, deletewriter);
		cusList2.getChildren().addAll(updateInfo, search);
		cusList3.getChildren().addAll(cusList, cusList2, returnto);
		b.getChildren().addAll(Title, VoiceActortable, cusList3);
		p.setCenter(b);
		Scene c = new Scene(p, 1200, 900, Color.BEIGE);
		primaryStage.setScene(c);
		primaryStage.show();
	}

	public void Writer(Stage primaryStage) {
		primaryStage.setMaximized(true);
		Wdata = new ArrayList<>();

		try {
			WritergetData();
			WdataList = FXCollections.observableArrayList(Wdata);
			WritertableView(primaryStage);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		primaryStage.setTitle("Writer");
		BorderPane p = new BorderPane();
		VBox b = new VBox(20);
		b.setPadding(new Insets(20, 20, 20, 20));
		VBox cusList = new VBox(10);
		VBox cusList2 = new VBox(10);
		HBox cusList3 = new HBox(30);
		Label Title = new Label("Writer table:");
		Title.setFont(Font.font("Times New Roman", 20));
		// Title.setPrefSize(80, 50);
		Button addWriter = new Button("Add writer");
		addWriter.setFont(Font.font("Times New Roman", 15));
		addWriter.setPrefSize(150, 30);
		addWriter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AddWriter(primaryStage);
			}
		});
		Button deletewriter = new Button("Delete writer");
		deletewriter.setFont(Font.font("Times New Roman", 15));
		deletewriter.setPrefSize(150, 30);
		deletewriter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					deletecus(primaryStage);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button updateInfo = new Button("Update Info");
		updateInfo.setFont(Font.font("Times New Roman", 15));
		updateInfo.setPrefSize(150, 30);
		updateInfo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateinf(primaryStage);
			}
		});
		Button search = new Button("Search");
		search.setPrefSize(150, 30);
		search.setFont(Font.font("Times New Roman", 15));
		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				searchcus(primaryStage);
			}
		});
		Button returnto = new Button("Return");
		returnto.setFont(Font.font("Times New Roman", 15));
		returnto.setPrefSize(150, 30);
		returnto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				mainPage(primaryStage);
			}
		});
		cusList.getChildren().addAll(addWriter, deletewriter);
		cusList2.getChildren().addAll(updateInfo, search);
		cusList3.getChildren().addAll(cusList, cusList2, returnto);
		b.getChildren().addAll(Title, Writertable, cusList3);
		p.setCenter(b);
		Scene c = new Scene(p, 1200, 900, Color.BEIGE);
		primaryStage.setScene(c);
		primaryStage.show();
	}

	public void Artist(Stage primaryStage) {
		primaryStage.setMaximized(true);

		try {
			ArtistgetData();
			ArdataList = FXCollections.observableArrayList(Ardata);
			ArtisttableView(primaryStage);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		primaryStage.setTitle("Artist");
		BorderPane p = new BorderPane();
		VBox b = new VBox(20);
		b.setPadding(new Insets(20, 20, 20, 20));
		VBox cusList = new VBox(10);
		VBox cusList2 = new VBox(10);
		HBox cusList3 = new HBox(30);
		Label Title = new Label("Artist table:");
		Title.setFont(Font.font("Times New Roman", 20));
		// Title.setPrefSize(80, 50);
		Button addWriter = new Button("Add artist");
		addWriter.setFont(Font.font("Times New Roman", 15));
		addWriter.setPrefSize(150, 30);
		addWriter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AddArtist(primaryStage);
			}
		});
		Button deletewriter = new Button("Delete artist");
		deletewriter.setFont(Font.font("Times New Roman", 15));
		deletewriter.setPrefSize(150, 30);
		deletewriter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				deleteArtist(primaryStage);
			}
		});
		Button updateInfo = new Button("Update Info");
		updateInfo.setFont(Font.font("Times New Roman", 15));
		updateInfo.setPrefSize(150, 30);
		updateInfo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateinfArtist(primaryStage);
			}
		});
		Button search = new Button("Search");
		search.setPrefSize(150, 30);
		search.setFont(Font.font("Times New Roman", 15));
		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				searchArtist(primaryStage);
			}
		});
		Button returnto = new Button("Return");
		returnto.setFont(Font.font("Times New Roman", 15));
		returnto.setPrefSize(150, 30);
		returnto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				mainPage(primaryStage);
			}
		});
		cusList.getChildren().addAll(addWriter, deletewriter);
		cusList2.getChildren().addAll(updateInfo, search);
		cusList3.getChildren().addAll(cusList, cusList2, returnto);
		b.getChildren().addAll(Title, Artisttable, cusList3);
		p.setCenter(b);
		Scene c = new Scene(p, Color.BEIGE);
		primaryStage.setScene(c);
		primaryStage.show();
	}

	public void AnimeAdd(Stage primaryStage) {
		primaryStage.setMaximized(true);
		Adata = new ArrayList<>();

		try {
			AnimegetData();
			AdataList = FXCollections.observableArrayList(Adata);
			AnimetableView(primaryStage);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		primaryStage.setTitle("Anime");
		BorderPane p = new BorderPane();
		VBox b = new VBox(20);
		b.setPadding(new Insets(20, 20, 20, 20));
		VBox cusList = new VBox(10);
		VBox cusList2 = new VBox(10);
		HBox cusList3 = new HBox(30);
		Label Title = new Label("Anime table:");
		Title.setFont(Font.font("Times New Roman", 20));
		// Title.setPrefSize(80, 50);
		Button addWriter = new Button("Add Anime");
		addWriter.setFont(Font.font("Times New Roman", 15));
		addWriter.setPrefSize(150, 30);
		addWriter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AddAnime(primaryStage);
			}
		});
		Button deletewriter = new Button("Delete Anime");
		deletewriter.setFont(Font.font("Times New Roman", 15));
		deletewriter.setPrefSize(150, 30);
		deletewriter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				deleteAnime(primaryStage);
			}
		});
		Button updateInfo = new Button("Update Info");
		updateInfo.setFont(Font.font("Times New Roman", 15));
		updateInfo.setPrefSize(150, 30);
		updateInfo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateinfAnime(primaryStage);
			}
		});
		Button search = new Button("Search");
		search.setPrefSize(150, 30);
		search.setFont(Font.font("Times New Roman", 15));
		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				searchAnime(primaryStage);
			}
		});
		Button returnto = new Button("Return");
		returnto.setFont(Font.font("Times New Roman", 15));
		returnto.setPrefSize(150, 30);
		returnto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				mainPage(primaryStage);
			}
		});
		cusList.getChildren().addAll(addWriter, deletewriter);
		cusList2.getChildren().addAll(updateInfo, search);
		cusList3.getChildren().addAll(cusList, cusList2, returnto);
		b.getChildren().addAll(Title, Animetable, cusList3);
		p.setCenter(b);
		Scene c = new Scene(p, Color.BEIGE);
		primaryStage.setScene(c);
		primaryStage.show();
	}

	public EventHandler<ActionEvent> AddAnime(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Add a new Anime");
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(00);
		Label L = new Label("Please enter Anime writer name, Title, and Copy right date:");
		L.setFont(Font.font("Times New Roman", 20));
		Label id1 = new Label("Anime writer name");
		id1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(20);
		gid.getChildren().addAll(id1, id);
		TextField name = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(00);

		Label Scene_ID = new Label("Scene_ID");
		Scene_ID.setFont(Font.font("Times New Roman", 20));
		HBox gid1 = new HBox(20);
		TextField Scene_ID1 = new TextField();
		Scene_ID1.setLayoutX(100);
		Scene_ID1.setLayoutY(00);
		gid1.getChildren().addAll(Scene_ID, Scene_ID1);

		Label Writer_ID = new Label("Writer_ID");
		Writer_ID.setFont(Font.font("Times New Roman", 20));
		HBox gid2 = new HBox(20);
		TextField writer_ID = new TextField();
		writer_ID.setLayoutX(100);
		writer_ID.setLayoutY(00);
		gid2.getChildren().addAll(Writer_ID, writer_ID);

		Label name1 = new Label("Title");
		name1.setFont(Font.font("Times New Roman", 20));
		HBox gname = new HBox(32);
		gname.getChildren().addAll(name1, name);
		TextField salary = new TextField();
		salary.setLayoutX(100);
		salary.setLayoutY(00);
		Label salary1 = new Label("Copy right date");
		salary1.setFont(Font.font("Times New Roman", 20));
		HBox gaddre = new HBox(12);
		gaddre.getChildren().addAll(salary1, salary);
		VBox list = new VBox(30);
		Label error = new Label();
		Label suc = new Label("Successfuly added");
		suc.setFont(Font.font("Times New Roman", 20));
		suc.setVisible(false);
		list.getChildren().addAll(L, gid, gname, gaddre, gid1, gid2, error, suc);
		list.setAlignment(Pos.TOP_LEFT);
		list.setPadding(new Insets(100, 0, 0, 50));
		ImageView ad = new ImageView("https://img.icons8.com/nolan/344/add.png");
		ad.setFitHeight(30);
		ad.setFitWidth(30);
		Button add = new Button("Add", ad);
		add.setFont(Font.font("Times New Roman", 15));
		error.setFont(Font.font("Times New Roman", 20));
		error.setVisible(false);
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					Anime r;
					r = new Anime(id.getText(), name.getText(), salary.getText(), Integer.valueOf(Scene_ID1.getText()),
							Integer.valueOf(writer_ID.getText()));
					AdataList.add(r);
					AnimeinsertData(r);
				} catch (NumberFormatException e) {
					error.setText("You entered a wrong value");
					error.setVisible(true);
				}
			}

			private void AnimeinsertData(Anime r) {
				// con.establishConnection();
				con.closeConnection();
				String insertSql = "INSERT INTO anime (name_of_writer, title, copy_right_data, SID, WID) VALUES (?, ?, ?, ?, ?)";
				try (Connection conn = con.getCon();
						PreparedStatement preparedStatement = conn.prepareStatement(insertSql)) {
					preparedStatement.setString(1, r.getName_Of_Writer());
					preparedStatement.setString(2, r.getTitle());
					preparedStatement.setString(3, r.getCopy_Right_Date());
					preparedStatement.setInt(4, r.getScene_ID());
					preparedStatement.setInt(5, r.getWriter_ID());
					System.out.println(r.getScene_ID() + "-" + r.getWriter_ID());
					var a = preparedStatement.executeUpdate();
					System.out.println(a);
				} catch (SQLException e) {
					System.out.println("Error inserting data into the database: " + e.getMessage());
				}
			}

			private void ExcuteStatement(String string) throws SQLException {
				con.establishConnection();
				con.executeUpdate(string);
			}
		});
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AnimeAdd(primaryStage);
			}
		});
		HBox buttons = new HBox(20);
		buttons.setPadding(new Insets(100, 0, 0, 50));
		buttons.getChildren().addAll(add, retu);
		VBox list1 = new VBox(150);
		list1.getChildren().addAll(list, buttons);
		StackPane sp = new StackPane();
		sp.getChildren().add(list1);
		Scene s = new Scene(sp);
		primaryStage.setScene(s);
		primaryStage.show();
		return null;
	}

	public EventHandler<ActionEvent> AddWriter(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Add a new writer");
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(00);
		Label L = new Label("Please enter writer ID, name, and salary:");
		L.setFont(Font.font("Times New Roman", 20));
		Label id1 = new Label("Writer ID");
		id1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(35);
		gid.getChildren().addAll(id1, id);
		TextField name = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(00);
		Label name1 = new Label("Writer name");
		name1.setFont(Font.font("Times New Roman", 20));
		HBox gname = new HBox(15);
		gname.getChildren().addAll(name1, name);
		TextField salary = new TextField();
		salary.setLayoutX(100);
		salary.setLayoutY(00);
		Label salary1 = new Label("Writer salary");
		salary1.setFont(Font.font("Times New Roman", 20));
		HBox gaddre = new HBox(10);
		gaddre.getChildren().addAll(salary1, salary);
		VBox list = new VBox(30);
		Label error = new Label();
		Label suc = new Label("Successfuly added");
		suc.setFont(Font.font("Times New Roman", 20));
		suc.setVisible(false);
		list.getChildren().addAll(L, gid, gaddre, gname, error, suc);
		list.setAlignment(Pos.TOP_LEFT);
		list.setPadding(new Insets(100, 0, 0, 50));
		ImageView ad = new ImageView("https://img.icons8.com/nolan/344/add.png");
		ad.setFitHeight(30);
		ad.setFitWidth(30);
		Button add = new Button("Add", ad);
		add.setFont(Font.font("Times New Roman", 15));
		error.setFont(Font.font("Times New Roman", 20));
		error.setVisible(false);
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					Writer r;
					r = new Writer(Integer.valueOf(id.getText()), Integer.valueOf(salary.getText()), name.getText());
					WdataList.add(r);
					WinsertData(r);
					suc.setVisible(true);
				} catch (NumberFormatException e) {
					error.setText("You entered a wrong value");
					error.setVisible(true);
				}
			}

			private void WinsertData(Writer r) {

				// con.establishConnection();
				con.closeConnection();
				String insertSql = "INSERT INTO writer (writer_ID, salary, name) VALUES (?, ?, ?)";
				try (Connection conn = con.getCon();
						PreparedStatement preparedStatement = conn.prepareStatement(insertSql)) {
					preparedStatement.setInt(1, r.getWriter_ID());
					preparedStatement.setInt(2, r.getWriter_Salary());
					preparedStatement.setString(3, r.getWriter_Name());
					preparedStatement.executeUpdate();

				} catch (SQLException e) {
					System.out.println("Error inserting data into the database: " + e.getMessage());
				}
			}

			private void ExcuteStatement(String string) throws SQLException {
				con.establishConnection();
				con.executeUpdate(string);
			}
		});
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Writer(primaryStage);
			}
		});
		HBox buttons = new HBox(20);
		buttons.setPadding(new Insets(100, 0, 0, 50));
		buttons.getChildren().addAll(add, retu);
		VBox list1 = new VBox(150);
		list1.getChildren().addAll(list, buttons);
		StackPane sp = new StackPane();
		sp.getChildren().add(list1);
		Scene s = new Scene(sp);
		primaryStage.setScene(s);
		primaryStage.show();
		return null;
	}

	public EventHandler<ActionEvent> AddArtist(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Add a new artist");
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(00);
		Label L = new Label("Please enter artist ID, name, and salary:");
		L.setFont(Font.font("Times New Roman", 20));
		Label id1 = new Label("Artist ID");
		id1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(35);
		gid.getChildren().addAll(id1, id);
		TextField name = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(00);
		Label name1 = new Label("Artist name");
		name1.setFont(Font.font("Times New Roman", 20));
		HBox gname = new HBox(15);
		gname.getChildren().addAll(name1, name);
		TextField salary = new TextField();
		salary.setLayoutX(100);
		salary.setLayoutY(00);
		Label salary1 = new Label("Artist salary");
		salary1.setFont(Font.font("Times New Roman", 20));
		HBox gaddre = new HBox(10);
		gaddre.getChildren().addAll(salary1, salary);
		VBox list = new VBox(30);
		Label error = new Label();
		Label suc = new Label("Successfuly added");
		suc.setFont(Font.font("Times New Roman", 20));
		suc.setVisible(false);
		list.getChildren().addAll(L, gid, gaddre, gname, error, suc);
		list.setAlignment(Pos.TOP_LEFT);
		list.setPadding(new Insets(100, 0, 0, 50));
		ImageView ad = new ImageView("https://img.icons8.com/nolan/344/add.png");
		ad.setFitHeight(30);
		ad.setFitWidth(30);
		Button add = new Button("Add", ad);
		add.setFont(Font.font("Times New Roman", 15));
		error.setFont(Font.font("Times New Roman", 20));
		error.setVisible(false);
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					Artist r;
					r = new Artist(Integer.valueOf(id.getText()), name.getText(), Integer.valueOf(salary.getText()));
					ArdataList.add(r);
					ArinsertData(r);
					suc.setVisible(true);
				} catch (NumberFormatException e) {
					error.setText("You entered a wrong value");
					error.setVisible(true);
				}
			}

			private void ArinsertData(Artist r) {
				// con.closeConnection();
				con.closeConnection();
				String insertSql = "INSERT INTO artist (artist_ID, name, salary) VALUES (?, ?, ?)";
				try (Connection conn = con.getCon();
						PreparedStatement preparedStatement = conn.prepareStatement(insertSql)) {
					preparedStatement.setInt(1, r.getArtist_ID());
					preparedStatement.setString(2, r.getArtist_Name());
					preparedStatement.setInt(3, r.getArtist_Salary());
					preparedStatement.executeUpdate();
					// con.closeConnection();
				} catch (SQLException e) {
					System.out.println("Error inserting data into the database: " + e.getMessage());
				}
			}

			private void ExcuteStatement(String string) throws SQLException {
				con.establishConnection();
				con.executeUpdate(string);
			}
		});
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(e -> {

			try {
				Artist(primaryStage);

			} catch (Exception e2) {
				System.out.println(e2);
			}

		});
		HBox buttons = new HBox(20);
		buttons.setPadding(new Insets(100, 0, 0, 50));
		buttons.getChildren().addAll(add, retu);
		VBox list1 = new VBox(150);
		list1.getChildren().addAll(list, buttons);
		StackPane sp = new StackPane();
		sp.getChildren().add(list1);
		Scene s = new Scene(sp);
		primaryStage.setScene(s);
		primaryStage.show();
		return null;
	}

	public EventHandler<ActionEvent> AddScene(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Add a new scene");
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(00);
		Label L = new Label("Please enter scene ID, Artist name, Period, start time, Episode number:");
		L.setFont(Font.font("Times New Roman", 20));
		Label id1 = new Label("Scene ID");
		id1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(25);
		gid.getChildren().addAll(id1, id);

		TextField name = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(00);
		Label name1 = new Label("Artist name");
		name1.setFont(Font.font("Times New Roman", 20));
		HBox gname = new HBox(15);
		gname.getChildren().addAll(name1, name);

		TextField Artist_ID = new TextField();
		Artist_ID.setLayoutX(100);
		Artist_ID.setLayoutY(00);
		Label artist_ID = new Label("Artist_ID");
		artist_ID.setFont(Font.font("Times New Roman", 20));
		HBox artist_ID1 = new HBox(15);
		artist_ID1.getChildren().addAll(artist_ID, Artist_ID);

		TextField salary = new TextField();
		salary.setLayoutX(100);
		salary.setLayoutY(00);
		Label salary1 = new Label("Period");
		salary1.setFont(Font.font("Times New Roman", 20));
		HBox gaddre = new HBox(40);
		gaddre.getChildren().addAll(salary1, salary);

		TextField Time = new TextField();
		Time.setLayoutX(100);
		Time.setLayoutY(00);
		Label sTime = new Label("Start time");
		sTime.setFont(Font.font("Times New Roman", 20));
		HBox gaddre1 = new HBox(20);
		gaddre1.getChildren().addAll(sTime, Time);

		TextField ep = new TextField();
		ep.setLayoutX(100);
		ep.setLayoutY(00);
		Label EP = new Label("Episode");
		EP.setFont(Font.font("Times New Roman", 20));
		HBox gaddre2 = new HBox(40);
		gaddre2.getChildren().addAll(EP, ep);

		Label error = new Label();
		Label suc = new Label("Successfuly added");
		VBox list = new VBox(30);
		suc.setFont(Font.font("Times New Roman", 20));
		suc.setVisible(false);
		list.getChildren().addAll(L, gid, gname, gaddre, gaddre1, gaddre2, artist_ID1, error, suc);
		list.setAlignment(Pos.TOP_LEFT);
		list.setPadding(new Insets(100, 0, 0, 50));
		ImageView ad = new ImageView("https://img.icons8.com/nolan/344/add.png");
		ad.setFitHeight(30);
		ad.setFitWidth(30);
		Button add = new Button("Add", ad);
		add.setFont(Font.font("Times New Roman", 15));
		error.setFont(Font.font("Times New Roman", 20));
		error.setVisible(false);
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					scene r;
					r = new scene(Integer.valueOf(id.getText()), name.getText(), Integer.parseInt(salary.getText()),
							Integer.valueOf(Time.getText()), Integer.valueOf(ep.getText()),
							Integer.valueOf(Artist_ID.getText()));
					sdataList.add(r);
					sinsertData(r);
					suc.setVisible(true);
				} catch (NumberFormatException e) {
					error.setText("You entered a wrong value");
					error.setVisible(true);
				}
			}

			private void sinsertData(scene r) {

				// con.establishConnection();
				con.closeConnection();
				String insertSql = "INSERT INTO scene (sID, artist_name, period, start_Time, episode, Artist_ID) VALUES (?, ?, ?, ?, ?, ?)";
				try (Connection conn = con.getCon();
						PreparedStatement preparedStatement = conn.prepareStatement(insertSql)) {
					preparedStatement.setInt(1, r.getScene_ID());
					preparedStatement.setString(2, r.getArtist_Name());
					preparedStatement.setInt(3, r.getPeriod());
					preparedStatement.setInt(4, r.getStart_Time());
					preparedStatement.setInt(5, r.getEpisode());
					preparedStatement.setInt(6, r.getArtist_ID());

					preparedStatement.executeUpdate();
				} catch (SQLException e) {
					System.out.println("Error inserting data into the database: " + e.getMessage());
				}

			}

			private void ExcuteStatement(String string) throws SQLException {
				con.establishConnection();
				con.executeUpdate(string);
			}
		});
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Scene(primaryStage);
			}
		});
		HBox buttons = new HBox(20);
		buttons.setPadding(new Insets(100, 0, 0, 50));
		buttons.getChildren().addAll(add, retu);
		VBox list1 = new VBox(100);
		list1.getChildren().addAll(list, buttons);
		StackPane sp = new StackPane();
		sp.getChildren().add(list1);
		Scene s = new Scene(sp);
		primaryStage.setScene(s);
		primaryStage.show();
		return null;
	}

	public EventHandler<ActionEvent> AddVC(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Add a new voice actor");
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(00);
		Label L = new Label("Please enter voice actor ID, Salary, Character Name, Address, Phone number:");
		L.setFont(Font.font("Times New Roman", 20));
		Label id1 = new Label("Voice actor ID");
		id1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(25);
		gid.getChildren().addAll(id1, id);
		TextField name = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(00);
		Label name1 = new Label("Character name");
		name1.setFont(Font.font("Times New Roman", 20));
		HBox gname = new HBox(15);
		gname.getChildren().addAll(name1, name);
		TextField salary = new TextField();
		salary.setLayoutX(100);
		salary.setLayoutY(00);
		Label salary1 = new Label("Salary");
		salary1.setFont(Font.font("Times New Roman", 20));
		TextField Time = new TextField();
		Time.setLayoutX(100);
		Time.setLayoutY(00);
		Label sTime = new Label("Address");
		sTime.setFont(Font.font("Times New Roman", 20));
		TextField ep = new TextField();
		ep.setLayoutX(100);
		ep.setLayoutY(00);
		Label EP = new Label("Phone number");
		EP.setFont(Font.font("Times New Roman", 20));
		HBox gaddre = new HBox(40);
		gaddre.getChildren().addAll(salary1, salary);
		HBox gaddre1 = new HBox(20);
		gaddre1.getChildren().addAll(sTime, Time);
		HBox gaddre2 = new HBox(40);
		gaddre2.getChildren().addAll(EP, ep);
		VBox list = new VBox(30);
		Label error = new Label();
		Label suc = new Label("Successfuly added");
		suc.setFont(Font.font("Times New Roman", 20));
		suc.setVisible(false);
		list.getChildren().addAll(L, gid, gaddre, gname, gaddre1, gaddre2, error, suc);
		list.setAlignment(Pos.TOP_LEFT);
		list.setPadding(new Insets(100, 0, 0, 50));
		ImageView ad = new ImageView("https://img.icons8.com/nolan/344/add.png");
		ad.setFitHeight(30);
		ad.setFitWidth(30);
		Button add = new Button("Add", ad);
		add.setFont(Font.font("Times New Roman", 15));
		error.setFont(Font.font("Times New Roman", 20));
		error.setVisible(false);
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					VoiceActor r;
					r = new VoiceActor(Integer.valueOf(id.getText()), Integer.parseInt(salary.getText()),
							name.getText(), Time.getText(), Integer.parseInt(ep.getText()));
					vcdataList.add(r);
					sinsertData(r);
					suc.setVisible(true);
				} catch (NumberFormatException e) {
					error.setText("You entered a wrong value");
					error.setVisible(true);
				}
			}

			private void sinsertData(VoiceActor r) {

				// con.establishConnection();
				con.closeConnection();
				String insertSql = "INSERT INTO voice_actor (VIP, salary, character_name, address, phone) VALUES (?, ?, ?, ?, ?)";
				try (Connection conn = con.getCon();
						PreparedStatement preparedStatement = conn.prepareStatement(insertSql)) {
					preparedStatement.setInt(1, r.getVoice_Actor_ID());
					preparedStatement.setInt(2, r.getVoice_Actor_Salary());
					preparedStatement.setString(3, r.getCharacter_Name());
					preparedStatement.setString(4, r.getAddress());
					preparedStatement.setInt(5, r.getPhone_Number());
					preparedStatement.executeUpdate();
				} catch (SQLException e) {
					System.out.println("Error inserting data into the database: " + e.getMessage());
				}

			}

			private void ExcuteStatement(String string) throws SQLException {
				con.establishConnection();
				;
				con.executeUpdate(string);
			}
		});
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				VC(primaryStage);
			}
		});
		HBox buttons = new HBox(20);
		buttons.setPadding(new Insets(100, 0, 0, 50));
		buttons.getChildren().addAll(add, retu);
		VBox list1 = new VBox(100);
		list1.getChildren().addAll(list, buttons);
		StackPane sp = new StackPane();
		sp.getChildren().add(list1);
		Scene s = new Scene(sp);
		primaryStage.setScene(s);
		primaryStage.show();
		return null;
	}

	String l = "Anime found!";

	public void deleteAnime(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Delete an anime");
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(20);
		se.setFitWidth(20);
		Label L2 = new Label("Please enter the anime writer name and Title:");
		L2.setFont(Font.font("Times New Roman", 20));
		HBox HB = new HBox(10);
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(30);
		Label id1 = new Label("Anime writer name");
		id1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(30);
		gid.getChildren().addAll(id1, id);
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(20);
		ret.setFitWidth(20);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.prefHeight(10);
		retu.prefWidth(10);
		HB.getChildren().addAll(retu);
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AnimeAdd(primaryStage);
			}
		});
		TextField name = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(50);
		Label name1 = new Label("Anime Title");
		name1.setFont(Font.font("Times New Roman", 20));
		HBox gname = new HBox(10);
		RadioButton r3 = new RadioButton("Find");
		r3.setFont(Font.font("Times New Roman", 15));
		gname.getChildren().addAll(name1, name, r3);
		VBox list = new VBox(20);
		list.setAlignment(Pos.TOP_LEFT);
		list.setPadding(new Insets(100, 0, 0, 50));
		ImageView de = new ImageView(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-delete-multimedia-kiranshastry-gradient-kiranshastry.png");
		de.setFitHeight(30);
		de.setFitWidth(30);
		Label find3 = new Label("Want to delete it?");
		find3.setFont(Font.font("Times New Roman", 15));
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		RadioButton r1 = new RadioButton("Yes");
		r1.setFont(Font.font("Times New Roman", 15));
		RadioButton r2 = new RadioButton("No");
		r2.setFont(Font.font("Times New Roman", 15));
		ToggleGroup tg = new ToggleGroup();
		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		r3.setToggleGroup(tg);
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(find3, r1, r2);
		list.getChildren().addAll(L2, gid, gname, t1, buttons);
		StackPane g = new StackPane();
		Label suc = new Label("Successfully deleted");
		suc.setFont(Font.font("Times New Roman", 15));
		r3.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.anime where title = '" + name.getText() + "'";
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {

						l += "Name of writer: " + rs.getString(1) + " Title: " + rs.getString(2) + " Copy right date: "
								+ rs.getString(3);
						t1.setText(l);

					}

				} else {
					t1.setText("Not found! Enter another name");
					r1.setDisable(true);
					r2.setDisable(true);
				}
			} catch (Exception e1) {
				t1.setText(e1.toString());
			}

		});
		suc.setVisible(false);
		r1.setOnAction(e -> {
			con.closeConnection();

			String deleteSQL = "DELETE FROM sys.anime WHERE title = ?";
			try (Connection conn = con.getCon();
					PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL)) {
				preparedStatement.setString(1, name.getText());
				preparedStatement.executeUpdate();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String l2 = "Suًccessfully deleted";
			t1.setText(l + "\n" + l2);

			r2.setOnAction(e1 -> {
				id.setText("");
				name.setText("");
			});

		});
		VBox v = new VBox(100);
		HB.setPadding(new Insets(0, 0, 20, 55));
		v.getChildren().addAll(list, HB);
		g.getChildren().addAll(v);
		Scene s1 = new Scene(g);
		primaryStage.setScene(s1);
		primaryStage.show();
		l = "";
	}

	String l2 = "Writer found!";

	public void deletecus(Stage primaryStage) throws SQLException {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Delete a writer");
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(20);
		se.setFitWidth(20);
		Label L2 = new Label("Please enter the writer ID and name:");
		L2.setFont(Font.font("Times New Roman", 20));
		HBox HB = new HBox(10);
		RadioButton find = new RadioButton("Find");
		find.setFont(Font.font("Times New Roman", 15));
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(30);
		Label id1 = new Label("Writer ID");
		id1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(30);
		gid.getChildren().addAll(id1, id);
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(20);
		ret.setFitWidth(20);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.prefHeight(10);
		retu.prefWidth(10);

		TextField name = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(50);
		Label name1 = new Label("Writer name");
		name1.setFont(Font.font("Times New Roman", 20));
		HBox gname = new HBox(10);
		gname.getChildren().addAll(name1, name, find);
		VBox list = new VBox(20);
		list.setAlignment(Pos.TOP_LEFT);
		list.setPadding(new Insets(100, 0, 0, 50));
		ImageView de = new ImageView(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-delete-multimedia-kiranshastry-gradient-kiranshastry.png");
		de.setFitHeight(30);
		de.setFitWidth(30);
		Label find3 = new Label("Want to delete it?");
		find3.setFont(Font.font("Times New Roman", 15));
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		RadioButton r1 = new RadioButton("Yes");
		r1.setFont(Font.font("Times New Roman", 15));
		RadioButton r2 = new RadioButton("No");
		r2.setFont(Font.font("Times New Roman", 15));
		find.setOnAction(e -> {
			try {
				String SQL = "Select * from sys.writer where Writer_ID = " + Integer.parseInt(id.getText());
				con.establishConnection();
				ResultSet rs = con.executeQuery(SQL);
				while (rs.next()) {
					l2 += "Writer ID: " + rs.getString(1) + " Writer salary: " + rs.getString(2) + " Writer name: "
							+ rs.getString(3);
					t1.setText(l2);

				}

			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		ToggleGroup tg = new ToggleGroup();
		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(find3, r1, r2);
		list.getChildren().addAll(L2, gid, gname, t1, buttons);
		StackPane g = new StackPane();
		r1.setOnAction(e -> {
			con.closeConnection();
			String deleteSQL = "DELETE FROM sys.writer WHERE Writer_ID = ?";
			try (Connection conn = con.getCon();
					PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL)) {
				preparedStatement.setInt(1, Integer.valueOf(id.getText()));
				preparedStatement.executeUpdate();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String l3 = "Successfully deleted";
			t1.setText(l2 + "\n" + l3);
			r2.setOnAction(e1 -> {
				id.setText("");
				name.setText("");
			});

		});
		l2 = "";
		HB.getChildren().addAll(find, retu);
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Writer(primaryStage);
			}
		});
		VBox v = new VBox(100);
		HB.setPadding(new Insets(0, 0, 20, 55));
		v.getChildren().addAll(list, HB);
		g.getChildren().addAll(v);
		Scene s = new Scene(g);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String l3 = "Atrist Found!";

	public void deleteArtist(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Delete an Artist");
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(20);
		se.setFitWidth(20);
		Label L2 = new Label("Please enter the artist ID and name:");
		L2.setFont(Font.font("Times New Roman", 20));
		HBox HB = new HBox(10);
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(30);
		Label id1 = new Label("Artist ID");
		id1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(30);
		gid.getChildren().addAll(id1, id);
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(20);
		ret.setFitWidth(20);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.prefHeight(10);
		retu.prefWidth(10);
		HB.getChildren().addAll(retu);

		TextField name = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(50);
		RadioButton find = new RadioButton("Find");
		Label name1 = new Label("Artist name");
		name1.setFont(Font.font("Times New Roman", 20));
		HBox gname = new HBox(10);
		gname.getChildren().addAll(name1, name, find);
		VBox list = new VBox(20);
		list.setAlignment(Pos.TOP_LEFT);
		list.setPadding(new Insets(100, 0, 0, 50));
		ImageView de = new ImageView(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-delete-multimedia-kiranshastry-gradient-kiranshastry.png");
		de.setFitHeight(30);
		de.setFitWidth(30);
		Label find3 = new Label("Want to delete it?");
		find3.setFont(Font.font("Times New Roman", 15));
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		RadioButton r1 = new RadioButton("Yes");
		r1.setFont(Font.font("Times New Roman", 15));
		RadioButton r2 = new RadioButton("No");
		r2.setFont(Font.font("Times New Roman", 15));
		find.setOnAction(e -> {
			try {
				String SQL = "Select * from sys.artist where Artist_ID = " + Integer.valueOf(id.getText());
				con.establishConnection();
				ResultSet rs = con.executeQuery(SQL);

				if (rs.next()) {
					while (rs.next()) {
						l3 += "Artist ID: " + rs.getString(1) + " Artist salary: " + rs.getString(2) + " Artist name: "
								+ rs.getString(3);
						t1.setText(l3);

					}
				} else {
					t1.setText("Not found! Enter another ID");
					r1.setDisable(true);
					r2.setDisable(true);
				}

			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		ToggleGroup tg = new ToggleGroup();
		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		find.setToggleGroup(tg);
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(find3, r1, r2);
		list.getChildren().addAll(L2, gid, gname, t1, buttons);
		StackPane g = new StackPane();
		r1.setOnAction(e -> {
			// con.establishConnection();
			con.closeConnection();
			String deleteSql = "DELETE FROM artist WHERE artist_ID = ?";
			try (Connection conn = con.getCon();
					PreparedStatement preparedStatement = conn.prepareStatement(deleteSql)) {
				preparedStatement.setInt(1, Integer.valueOf(id.getText()));
				preparedStatement.executeUpdate();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				System.out.println("Error deleting data from the database: " + e2.getMessage());

			}

			r2.setOnAction(e1 -> {
				id.setText("");
				name.setText("");
			});

		});
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Artist(primaryStage);
			}
		});

		// l3 = "";
		VBox v = new VBox(100);
		HB.setPadding(new Insets(0, 0, 20, 55));
		v.getChildren().addAll(list, HB);
		g.getChildren().addAll(v);
		Scene s = new Scene(g);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String l5 = "Scene Found!";

	public void deleteScene(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Delete a scene");
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(20);
		se.setFitWidth(20);
		Label L2 = new Label("Please enter the scene ID:");
		L2.setFont(Font.font("Times New Roman", 20));
		HBox HB = new HBox(10);
		RadioButton find = new RadioButton("Find");
		find.setFont(Font.font("Times New Roman", 15));
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(30);
		Label id1 = new Label("Scene ID");
		id1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(30);
		gid.getChildren().addAll(id1, id, find);
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(20);
		ret.setFitWidth(20);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.prefHeight(10);
		retu.prefWidth(10);
		HB.getChildren().addAll(retu);
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Scene(primaryStage);
			}
		});
		TextField name = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(50);
		VBox list = new VBox(20);
		list.setAlignment(Pos.TOP_LEFT);
		list.setPadding(new Insets(100, 0, 0, 50));
		ImageView de = new ImageView(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-delete-multimedia-kiranshastry-gradient-kiranshastry.png");
		de.setFitHeight(30);
		de.setFitWidth(30);
		Label find3 = new Label("Want to delete it?");
		find3.setFont(Font.font("Times New Roman", 15));
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		RadioButton r1 = new RadioButton("Yes");
		r1.setFont(Font.font("Times New Roman", 15));
		RadioButton r2 = new RadioButton("No");
		r2.setFont(Font.font("Times New Roman", 15));
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "select * from sys.scene where sID = " + Integer.parseInt(id.getText());
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						l5 += "Scene ID: " + rs.getString(1) + " Artist name: " + rs.getString(2) + " Period: "
								+ rs.getString(3) + " Start time: " + rs.getString(4) + " Episode: " + rs.getString(5)
								+ "Artist_ID:" + rs.getString(columnsNumber);
						t1.setText(l5);
					}
				} else {
					t1.setText("Not found! Enter another ID");
					r1.setDisable(true);
					r2.setDisable(true);
				}
			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		ToggleGroup tg = new ToggleGroup();
		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		find.setToggleGroup(tg);
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(find3, r1, r2);
		list.getChildren().addAll(L2, gid, t1, buttons);
		StackPane g = new StackPane();
		r1.setOnAction(e -> {
			con.closeConnection();
			String deleteSQL = "DELETE FROM sys.scene WHERE sID = ?";
			try (Connection conn = con.getCon();
					PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL)) {
				preparedStatement.setInt(1, Integer.valueOf(id.getText()));
				preparedStatement.executeUpdate();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String l6 = "Successfully deleted";
			t1.setText(l5 + "\n" + l6);
			r2.setOnAction(e1 -> {
				id.setText("");
			});

		});
		l5 = "";
		VBox v = new VBox(100);
		HB.setPadding(new Insets(0, 0, 20, 55));
		v.getChildren().addAll(list, HB);
		g.getChildren().addAll(v);
		Scene s = new Scene(g);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String l7 = "Voice actor Found!";

	public void deleteVC(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Delete a voice actor");
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(20);
		se.setFitWidth(20);
		Label L2 = new Label("Please enter the Voice actor ID:");
		L2.setFont(Font.font("Times New Roman", 20));
		HBox HB = new HBox(10);
		Label id1 = new Label("Voice actor ID");
		id1.setFont(Font.font("Times New Roman", 20));
		RadioButton find = new RadioButton("Find");
		find.setFont(Font.font("Times New Roman", 15));
		find.prefHeight(10);
		find.prefWidth(10);
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(30);
		HBox gid = new HBox(30);
		gid.getChildren().addAll(id1, id, find);
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(20);
		ret.setFitWidth(20);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.prefHeight(10);
		retu.prefWidth(10);
		HB.getChildren().addAll(retu);
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				VC(primaryStage);
			}
		});
		TextField name = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(50);
		VBox list = new VBox(20);
		list.setAlignment(Pos.TOP_LEFT);
		list.setPadding(new Insets(100, 0, 0, 50));
		ImageView de = new ImageView(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-delete-multimedia-kiranshastry-gradient-kiranshastry.png");
		de.setFitHeight(30);
		de.setFitWidth(30);
		Label find3 = new Label("Want to delete it?");
		find3.setFont(Font.font("Times New Roman", 15));
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		RadioButton r1 = new RadioButton("Yes");
		r1.setFont(Font.font("Times New Roman", 15));
		RadioButton r2 = new RadioButton("No");
		r2.setFont(Font.font("Times New Roman", 15));
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.voice_actor where VIP = " + Integer.parseInt(id.getText());
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						l7 += "Voice acotr ID: " + rs.getString(1) + " Voice actor salary: " + rs.getString(2)
								+ " Character name: " + rs.getString(3) + " Address: " + rs.getString(4)
								+ " Phone number: " + rs.getShort(5);
						t1.setText(l7);
					}
				} else {
					t1.setText("Not found! Enter another ID");
					r1.setDisable(true);
					r2.setDisable(true);
				}
			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		ToggleGroup tg = new ToggleGroup();
		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(find3, r1, r2);
		list.getChildren().addAll(L2, gid, t1, buttons);
		StackPane g = new StackPane();
		r1.setOnAction(e -> {
			con.closeConnection();
			String deleteSQL = "DELETE FROM sys.voice_actor WHERE VIP = ?";
			try (Connection conn = con.getCon();
					PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL)) {
				preparedStatement.setInt(1, Integer.valueOf(id.getText()));
				preparedStatement.executeUpdate();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String l8 = "Successfully deleted";
			t1.setText(l7 + "\n" + l8);
			r2.setOnAction(e1 -> {
				id.setText("");
			});

		});
		l7 = "";
		VBox v = new VBox(100);
		HB.setPadding(new Insets(0, 0, 20, 55));
		v.getChildren().addAll(list, HB);
		g.getChildren().addAll(v);
		Scene s = new Scene(g);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String l9 = "Anime Found!";

	public void updateinfAnime(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Update an anime info");
		Label L = new Label("Please enter the anime writer name and Title:");
		L.setFont(Font.font("Times New Roman", 20));
		Label L2 = new Label("Please Enter the new anime writer name, Title, and copy right date:");
		L2.setFont(Font.font("Times New Roman", 20));
		TextField id = new TextField();
		TextField id2 = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(00);
		RadioButton find = new RadioButton("Find");
		find.setFont(Font.font("Times New Roman", 15));
		Label id1 = new Label("Anime writer name");
		id1.setFont(Font.font("Times New Roman", 20));
		Label id3 = new Label("New Anime writer name");
		id3.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(31);
		HBox gid2 = new HBox(31);
		gid.getChildren().addAll(id1, id);
		gid2.getChildren().addAll(id3, id2);
		TextField name = new TextField();
		TextField name2 = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(00);
		Label name1 = new Label("Animt Title");
		TextField sid1 = new TextField();
		TextField wid1 = new TextField();
		Label sid = new Label("new SID");
		Label wid = new Label(" new WID");
		sid1.setLayoutX(100);
		sid1.setLayoutY(00);
		wid1.setLayoutX(100);
		wid1.setLayoutY(00);
		sid.setFont(Font.font("Times New Roman", 20));
		wid.setFont(Font.font("Times New Roman", 20));

		HBox sid2 = new HBox(31);
		sid2.getChildren().addAll(sid, sid1);
		HBox wid2 = new HBox(31);
		wid2.getChildren().addAll(wid, wid1);

		Label name3 = new Label("New Anime Title");
		name1.setFont(Font.font("Times New Roman", 20));
		name3.setFont(Font.font("Times New Roman", 20));
		Label salary1 = new Label("New Anime copy right date");
		salary1.setFont(Font.font("Times New Roman", 20));
		TextField sal = new TextField();
		name3.setFont(Font.font("Times New Roman", 20));
		HBox gname = new HBox(10);
		gname.getChildren().addAll(name1, name, find);
		HBox gname2 = new HBox(15);
		HBox gsal = new HBox(10);
		HBox g = new HBox(13);
		g.getChildren().addAll(name3, name2);
		gsal.getChildren().addAll(salary1, sal);
		gname2.getChildren().addAll(g, gsal);
		VBox list = new VBox(20);
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(30);
		se.setFitWidth(30);
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.anime where title = '" + name.getText() + "'";
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						l9 += "Name of writer: " + rs.getString(1) + " Title: " + rs.getString(2) + " Copy right date: "
								+ rs.getString(3) + " sID: " + rs.getString(4) + " wID: " + rs.getString(5);
						t1.setText(l9);
					}
				} else {
					t1.setText("Not found! Enter another name");
				}
			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		VBox list2 = new VBox(20);
		list2.getChildren().addAll(L, gid, gname);
		list.getChildren().addAll(L2, gid2, gname2, gsal, sid2, wid2);
		HBox H = new HBox(30);
		H.setPadding(new Insets(100, 0, 0, 50));
		H.setAlignment(Pos.TOP_LEFT);
		ImageView up = new ImageView("https://img.icons8.com/nolan/344/update-tag.png");
		Button upd = new Button("Update", up);
		H.getChildren().addAll(list2, list);
		up.setFitHeight(30);
		up.setFitWidth(30);
		upd.setFont(Font.font("Times New Roman", 15));
		Label error = new Label();
		error.setFont(Font.font("Times New Roman", 20));
		error.setVisible(false);
		upd.setOnAction(e -> {
			try {
				// con.establishConnection();
				con.closeConnection();
				String SQL = "update sys.anime set name_of_writer = '" + id2.getText() + "', copy_right_data = '"
						+ sal.getText() +"'where title = '" + name.getText() + "'";
				try (Connection conn = con.getCon(); PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
					preparedStatement.executeUpdate();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					System.out.println("Error deleting data from the database: " + e2.getMessage());

				}
				String l10 = "Successfully updated";
				t1.setText(l10 + "\n" + l9 + "\n" + "Update: " + "Name of writer: " + id2.getText() + " title: "
						+ sal.getText() + " Copy of right date: " + name2.getText());

			} catch (Exception e1) {
				error.setText("You entered a wrong value");
				error.setVisible(true);
			}

		});
		l9 = "";
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AnimeAdd(primaryStage);
			}
		});
		VBox b = new VBox(20);
		VBox b2 = new VBox(20);
		b2.getChildren().addAll(upd, t1, error);
		b.getChildren().addAll(H, b2);
		b2.setPadding(new Insets(0, 0, 20, 55));
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(retu);
		buttons.setPadding(new Insets(0, 0, 20, 58));
		VBox g2 = new VBox(100);
		g2.getChildren().addAll(b, buttons);
		Scene s = new Scene(g2);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String l11 = "Writer Found!";

	public void updateinf(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Update a writer info");
		Label L = new Label("PLease enter the writer id and name:");
		L.setFont(Font.font("Times New Roman", 20));
		Label L2 = new Label("Please Enter the new writer ID, Name, Salary:");
		L2.setFont(Font.font("Times New Roman", 20));
		TextField id = new TextField();
		TextField id2 = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(00);
		Label id1 = new Label("Writer ID");
		id1.setFont(Font.font("Times New Roman", 20));
		Label id3 = new Label("New Writer ID");
		id3.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(31);
		HBox gid2 = new HBox(31);
		gid.getChildren().addAll(id1, id);
		gid2.getChildren().addAll(id3, id2);
		TextField name = new TextField();
		TextField name2 = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(00);
		RadioButton find = new RadioButton("Find");
		find.setFont(Font.font("Times New Roman", 15));
		Label name1 = new Label("Writer name");
		Label name3 = new Label("New writer name");
		name1.setFont(Font.font("Times New Roman", 20));
		name3.setFont(Font.font("Times New Roman", 20));
		Label salary1 = new Label("New writer salary");
		salary1.setFont(Font.font("Times New Roman", 20));
		TextField sal = new TextField();
		name3.setFont(Font.font("Times New Roman", 20));
		HBox gname = new HBox(10);
		gname.getChildren().addAll(name1, name, find);
		HBox gname2 = new HBox(15);
		HBox gsal = new HBox(10);
		HBox g = new HBox(13);
		g.getChildren().addAll(name3, name2);
		gsal.getChildren().addAll(salary1, sal);
		gname2.getChildren().addAll(g, gsal);
		VBox list = new VBox(20);
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.writer where Writer_ID = " + Integer.parseInt(id.getText());
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						l11 += "Writer ID: " + rs.getString(1) + " Writer salary: " + rs.getString(2) + " Writer name: "
								+ rs.getString(3);
						t1.setText(l11);
					}
				} else {
					t1.setText("Not found! Enter another ID");
				}
			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		VBox list2 = new VBox(20);
		list2.getChildren().addAll(L, gid, gname);
		list.getChildren().addAll(L2, gid2, gname2, gsal);
		HBox H = new HBox(30);
		H.setPadding(new Insets(100, 0, 0, 50));
		H.setAlignment(Pos.TOP_LEFT);
		ImageView up = new ImageView("https://img.icons8.com/nolan/344/update-tag.png");
		Button upd = new Button("Update", up);
		H.getChildren().addAll(list2, list);
		up.setFitHeight(30);
		up.setFitWidth(30);
		upd.setFont(Font.font("Times New Roman", 15));
		Label error = new Label();
		error.setFont(Font.font("Times New Roman", 20));
		error.setVisible(false);
		upd.setOnAction(e -> {
			try {
				// con.establishConnection();
				con.closeConnection();
				String SQL = "update sys.writer set   salary = " + Integer.parseInt(sal.getText()) + ", name = '"
						+ name2.getText() + "'where writer_ID = " + Integer.parseInt(id.getText());
				try (Connection conn = con.getCon(); PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
					preparedStatement.executeUpdate();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					System.out.println("Error deleting data from the database: " + e2.getMessage());

				}
				String l12 = "Successfully updated";
				t1.setText(l12 + "\n" + l11 + "\n" + "Update: " + "Writer ID: " + id2.getText() + " Writer salary: "
						+ sal.getText() + " Writer name: " + name2.getText());

			} catch (Exception e1) {
				error.setText("You entered a wrong value");
				error.setVisible(true);
			}

			String l12 = "Successfully updated";
			t1.setText(l12 + "\n" + l11 + "\n" + "Update: " + "Writer ID: " + id2.getText() + " Writer salary: "
					+ sal.getText() + " Writer name: " + name2.getText());

		});
		l11 = "";
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Writer(primaryStage);
			}
		});
		VBox b = new VBox(20);
		VBox b2 = new VBox(20);
		b2.getChildren().addAll(upd, t1, error);
		b.getChildren().addAll(H, b2);
		b2.setPadding(new Insets(0, 0, 20, 55));
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(retu);
		buttons.setPadding(new Insets(0, 0, 20, 58));
		VBox g2 = new VBox(100);
		g2.getChildren().addAll(b, buttons);
		Scene s = new Scene(g2);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String l13 = "Artist Found!";

	public void updateinfArtist(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Update an artist info");
		Label L = new Label("PLease enter the artist id and name:");
		L.setFont(Font.font("Times New Roman", 20));
		Label L2 = new Label("Please Enter the new artist ID, Name, Salary:");
		L2.setFont(Font.font("Times New Roman", 20));
		TextField id = new TextField();
		TextField id2 = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(00);
		Label id1 = new Label("Artist ID");
		id1.setFont(Font.font("Times New Roman", 20));
		Label id3 = new Label("New artist ID");
		id3.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(31);
		HBox gid2 = new HBox(31);
		gid.getChildren().addAll(id1, id);
		gid2.getChildren().addAll(id3, id2);
		TextField name = new TextField();
		TextField name2 = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(00);
		Label name1 = new Label("Artist name");
		Label name3 = new Label("New artist name");
		name1.setFont(Font.font("Times New Roman", 20));
		name3.setFont(Font.font("Times New Roman", 20));
		Label salary1 = new Label("New artist salary");
		salary1.setFont(Font.font("Times New Roman", 20));
		TextField sal = new TextField();
		name3.setFont(Font.font("Times New Roman", 20));
		HBox gname = new HBox(10);
		RadioButton find = new RadioButton("find");
		gname.getChildren().addAll(name1, name, find);
		HBox gname2 = new HBox(15);
		HBox gsal = new HBox(10);
		HBox g = new HBox(13);
		g.getChildren().addAll(name3, name2);
		gsal.getChildren().addAll(salary1, sal);
		gname2.getChildren().addAll(g, gsal);
		VBox list = new VBox(20);
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(30);
		se.setFitWidth(30);
		find.setFont(Font.font("Times New Roman", 15));
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.artist where artist_ID = " + Integer.parseInt(id.getText());
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						l13 = "Artist ID: " + rs.getString(1) + " Artist salary: " + rs.getString(2)
								+ " Artist Salary: " + rs.getString(3);
						t1.setText(l13);
					}
				} else {
					t1.setText("Not found! Enter another ID");
				}
			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		VBox list2 = new VBox(20);
		list2.getChildren().addAll(L, gid, gname);
		list.getChildren().addAll(L2, gid2, gname2, gsal);
		HBox H = new HBox(30);
		H.setPadding(new Insets(100, 0, 0, 50));
		H.setAlignment(Pos.TOP_LEFT);
		ImageView up = new ImageView("https://img.icons8.com/nolan/344/update-tag.png");
		Button upd = new Button("Update", up);
		H.getChildren().addAll(list2, list);
		up.setFitHeight(30);
		up.setFitWidth(30);
		upd.setFont(Font.font("Times New Roman", 15));
		Label error = new Label();
		error.setFont(Font.font("Times New Roman", 20));
		error.setVisible(false);
		upd.setOnAction(e -> {
			try {
				// con.establishConnection();
				con.closeConnection();
				String SQL = "update sys.artist set salary = " + Integer.parseInt(sal.getText()) + ", name = '"
						+ name2.getText() + "'where artist_ID = " + Integer.parseInt(id.getText());
				try (Connection conn = con.getCon(); PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
					preparedStatement.executeUpdate();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					System.out.println("Error deleting data from the database: " + e2.getMessage());

				}
				String l14 = "Successfully updated";
				t1.setText(l14 + "\n" + l13 + "\n" + "Update: " + "Artist ID: " + id2.getText() + " Artist name: "
						+ name2.getText() + " Artist salary: " + sal.getText());

			} catch (Exception e1) {
				error.setText("You entered a wrong value");
				error.setVisible(true);
			}

		});
		l13 = "";
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Artist(primaryStage);
			}
		});
		VBox b = new VBox(20);
		VBox b2 = new VBox(20);
		b2.getChildren().addAll(upd, t1, error);
		b.getChildren().addAll(H, b2);
		b2.setPadding(new Insets(0, 0, 20, 55));
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(retu);
		buttons.setPadding(new Insets(0, 0, 20, 58));
		VBox g2 = new VBox(100);
		g2.getChildren().addAll(b, buttons);
		Scene s = new Scene(g2);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String l15 = "Scene Found!";

	public void updateinfScene(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Update a Scene Info");
		Label L = new Label("Please enter the Scene ID:");
		L.setFont(Font.font("Times New Roman", 20));
		Label L2 = new Label("Please Enter the new Scene ID, Artist Name, Start Time, Period, and Episode:");
		L2.setFont(Font.font("Times New Roman", 20));
		TextField id = new TextField();
		TextField id2 = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(00);
		Label Period = new Label("New Scene period");
		Period.setFont(Font.font("Times New Roman", 20));
		TextField p = new TextField();
		Label Episode = new Label("New Scene Episode");
		Episode.setFont(Font.font("Times New Roman", 20));
		TextField ep = new TextField();
		Episode.setFont(Font.font("Times New Roman", 20));
		HBox H = new HBox(10);
		H.getChildren().addAll(Period, p);
		HBox H2 = new HBox(10);
		H2.getChildren().addAll(Episode, ep);
		VBox V = new VBox(20);
		V.getChildren().addAll(H, H2);
		Label id1 = new Label("Scene ID");
		id1.setFont(Font.font("Times New Roman", 20));
		Label id3 = new Label("New scene ID");
		id3.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(31);
		HBox gid2 = new HBox(27);
		gid.getChildren().addAll(id1, id);
		gid2.getChildren().addAll(id3, id2);
		TextField name = new TextField();
		TextField name2 = new TextField();
		name.setLayoutX(100);
		name.setLayoutY(00);
		Label name1 = new Label("Artist name");

		TextField artist_id1 = new TextField();
		artist_id1.setLayoutX(100);
		artist_id1.setLayoutY(00);
		Label artist_id = new Label("new Artist_ID");
		HBox artist_id2 = new HBox(27);
		artist_id2.getChildren().addAll(artist_id, artist_id1);
		artist_id.setFont(Font.font("Times New Roman", 20));

		Label name3 = new Label("New Artist name");
		name1.setFont(Font.font("Times New Roman", 20));
		name3.setFont(Font.font("Times New Roman", 20));
		Label salary1 = new Label("New scene start time");
		salary1.setFont(Font.font("Times New Roman", 20));
		TextField sal = new TextField();
		HBox gname = new HBox(17);
		RadioButton find = new RadioButton("Find");
		gname.getChildren().addAll(name1, name, find);
		HBox gname2 = new HBox(15);
		HBox gsal = new HBox(10);
		HBox g = new HBox(13);

		g.getChildren().addAll(name3, name2);
		gsal.getChildren().addAll(salary1, sal);

		gname2.getChildren().addAll(g, gsal);
		VBox list = new VBox(20);
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		find.setFont(Font.font("Times New Roman", 15));
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.scene where sID = " + Integer.parseInt(id.getText());
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						l15 += "\n" + "Scene ID: " + rs.getString(1) + " Artist name: " + rs.getString(2) + " Period: "
								+ rs.getString(3) + " Start time: " + rs.getString(4) + " Epiosde: " + rs.getString(5);
						t1.setText(l15);
					}
				} else {
					t1.setText("Not found! Enter another ID");
				}
			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		VBox list2 = new VBox(20);
		list2.getChildren().addAll(L, gid, gname);
		VBox o = new VBox(30);
		HBox VB = new HBox(20);
		list.getChildren().addAll(gid2, gname2, gsal);
		VB.getChildren().addAll(list, V);
		o.getChildren().addAll(L2, VB);
		HBox H1 = new HBox(30);
		H1.setPadding(new Insets(100, 0, 0, 50));
		H1.setAlignment(Pos.TOP_LEFT);
		ImageView up = new ImageView("https://img.icons8.com/nolan/344/update-tag.png");
		Button upd = new Button("Update", up);
		H1.getChildren().addAll(list2, o);
		up.setFitHeight(30);
		up.setFitWidth(30);
		upd.setFont(Font.font("Times New Roman", 15));
		Label error = new Label();
		error.setFont(Font.font("Times New Roman", 20));
		error.setVisible(false);
		upd.setOnAction(e -> {
			try {
				// con.establishConnection();
				con.closeConnection();
				String SQL = "update sys.scene set  artist_name = '" + name2.getText() + "', period = "
						+ Integer.parseInt(p.getText()) + ", start_Time = " + Integer.parseInt(sal.getText())
						+ ", episode = " + Integer.parseInt(ep.getText()) + " where sID = "
						+ Integer.parseInt(id.getText());
				try (Connection conn = con.getCon(); PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
					preparedStatement.executeUpdate();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					System.out.println("Error deleting data from the database: " + e2.getMessage());

				}
				String l12 = "Successfully updated";
				t1.setText(l12 + "\n" + l15 + "\n" + "Update: " + "Scene ID: " + id2.getText() + " Artist name: "
						+ name2.getText() + " Period: " + p.getText() + " Episode: " + ep.getText());

			} catch (Exception e1) {
				error.setText("You entered a wrong value");
				error.setVisible(true);
			}

		});
		l15 = "";
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Scene(primaryStage);
			}
		});
		VBox b = new VBox(20);
		VBox b2 = new VBox(20);
		b2.getChildren().addAll(upd, t1, error);
		b.getChildren().addAll(H1, b2);
		b2.setPadding(new Insets(0, 0, 20, 55));
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(retu);
		buttons.setPadding(new Insets(0, 0, 20, 58));
		VBox g2 = new VBox(100);
		g2.getChildren().addAll(b, buttons);
		Scene s = new Scene(g2);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String v = "Voice actor Found!";

	public void updateinfVC(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Update a Voice actor Info");
		Label L = new Label("Please enter the Voice actor ID:");
		L.setFont(Font.font("Times New Roman", 20));
		Label L2 = new Label("Please Enter the new Voice actor ID, Salary, Character name, Address, and Phone number:");
		L2.setFont(Font.font("Times New Roman", 20));
		TextField id = new TextField();
		TextField id2 = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(00);
		Label Period = new Label("New Address");
		Period.setFont(Font.font("Times New Roman", 20));
		TextField p = new TextField();
		Label Episode = new Label("New Phone number");
		Episode.setFont(Font.font("Times New Roman", 20));
		TextField ep = new TextField();
		Episode.setFont(Font.font("Times New Roman", 20));
		HBox H = new HBox(10);
		H.getChildren().addAll(Period, p);
		HBox H2 = new HBox(10);
		H2.getChildren().addAll(Episode, ep);
		VBox V = new VBox(20);
		V.getChildren().addAll(H, H2);
		Label id1 = new Label("Voice actor ID");
		id1.setFont(Font.font("Times New Roman", 20));
		Label id3 = new Label("New Voice actor ID");
		id3.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(31);
		HBox gid2 = new HBox(27);
		RadioButton find = new RadioButton("Find");
		gid.getChildren().addAll(id1, id, find);
		gid2.getChildren().addAll(id3, id2);
		TextField name2 = new TextField();

		Label name3 = new Label("New salary");
		name3.setFont(Font.font("Times New Roman", 20));
		Label salary1 = new Label("New Character name");
		salary1.setFont(Font.font("Times New Roman", 20));
		TextField sal = new TextField();
		HBox gname2 = new HBox(15);
		HBox gsal = new HBox(10);
		HBox g = new HBox(13);
		g.getChildren().addAll(name3, name2);
		gsal.getChildren().addAll(salary1, sal);

		find.setFont(Font.font("Times New Roman", 15));
		gname2.getChildren().addAll(g, gsal);
		VBox list = new VBox(20);
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(30);
		se.setFitWidth(30);
		VBox list2 = new VBox(20);
		list2.getChildren().addAll(L, gid);
		VBox o = new VBox(30);
		HBox VB = new HBox(20);
		list.getChildren().addAll(gid2, gname2, gsal);
		VB.getChildren().addAll(list, V);
		o.getChildren().addAll(L2, VB);
		HBox H1 = new HBox(30);
		H1.setPadding(new Insets(100, 0, 0, 50));
		H1.setAlignment(Pos.TOP_LEFT);
		Label suc = new Label("Suًccessfully updated");
		suc.setFont(Font.font("Times New Roman", 20));
		suc.setVisible(false);
		ImageView up = new ImageView("https://img.icons8.com/nolan/344/update-tag.png");
		Button upd = new Button("Update", up);
		H1.getChildren().addAll(list2, o);
		up.setFitHeight(30);
		up.setFitWidth(30);
		upd.setFont(Font.font("Times New Roman", 15));
		Label error = new Label();
		error.setFont(Font.font("Times New Roman", 20));
		error.setVisible(false);
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.voice_actor where VIP = " + Integer.parseInt(id.getText());
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						v += "\nVoice actor ID: " + rs.getString(1) + " Voice actor salary: " + rs.getString(2)
								+ " Character name: " + rs.getString(3) + " Address" + rs.getString(4)
								+ " Phone number: " + rs.getString(5);
						t1.setText(v);
					}
				} else {
					t1.setText("Not found! Enter another ID");
				}
			} catch (Exception e1) {
				error.setText("You entered a wrong value");
				error.setVisible(true);
			}
		});
		upd.setOnAction(e -> {
			try {
				// con.establishConnection();
				con.closeConnection();
				String SQL = "update sys.voice_actor set  salary = " + Integer.parseInt(name2.getText())
						+ ", character_name = '" + sal.getText() + "', address = '" + p.getText() + "', phone = "
						+ Integer.parseInt(ep.getText()) + " where VIP = " + Integer.parseInt(id.getText());
				try (Connection conn = con.getCon(); PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
					preparedStatement.executeUpdate();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					System.out.println("Error deleting data from the database: " + e2.getMessage());

				}
				String l12 = "Successfully updated";
				t1.setText(l12 + "\n" + v + "\n" + "Update: " + "Voice actor ID: " + id2.getText() + " Salary: "
						+ name2.getText() + " Character name: " + sal.getText() + " Address: " + p.getText()
						+ " Phone number: " + ep.getText());

			} catch (Exception e1) {
				error.setText("You entered a wrong value");
				error.setVisible(true);
			}

		});
		v = "";
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				VC(primaryStage);
			}
		});
		VBox b = new VBox(20);
		VBox b2 = new VBox(20);
		b2.getChildren().addAll(upd, t1, suc, error);
		b.getChildren().addAll(H1, b2);
		b2.setPadding(new Insets(0, 0, 20, 55));
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(retu);
		buttons.setPadding(new Insets(0, 0, 20, 58));
		VBox g2 = new VBox(100);
		g2.getChildren().addAll(b, buttons);
		Scene s = new Scene(g2);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String S = "Scene Found!";

	public void searchScene(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Search for a scene");
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(0);
		Label id1 = new Label("Scene ID");
		id1.setFont(Font.font("Times New Roman", 20));
		Label L1 = new Label("Please enter the Scene ID and Artist name:");
		L1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(10);
		gid.getChildren().addAll(id1, id);
		VBox t = new VBox(25);
		t.getChildren().addAll(L1, gid);
		t.setPadding(new Insets(50, 0, 0, 50));
		t.setAlignment(Pos.TOP_LEFT);
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(30);
		se.setFitWidth(30);
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		Button find = new Button("Search", se);
		find.setFont(Font.font("Times New Roman", 15));
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.scene where sID = " + Integer.parseInt(id.getText());
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						S += "\nScene ID: " + rs.getString(1) + " Artist Name: " + rs.getString(2) + " Period: "
								+ rs.getString(3) + " Start Time" + rs.getString(4) + " Episode: " + rs.getString(5);
						t1.setText(S);
					}
				} else {
					t1.setText("Not found! Enter another ID");
				}
			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Scene(primaryStage);
			}
		});
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(retu);
		buttons.setPadding(new Insets(0, 0, 20, 58));
		VBox g1 = new VBox(25);
		g1.getChildren().addAll(find, t1);
		g1.setPadding(new Insets(0, 0, 20, 58));
		VBox g = new VBox(25);
		VBox g2 = new VBox(200);
		g.getChildren().addAll(t, g1);
		g2.getChildren().addAll(g, buttons);
		Scene s = new Scene(g2);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String q = "Voice Actor Found!";

	public void searchVC(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Search for a scene");
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(0);
		Label id1 = new Label("Scene ID");
		id1.setFont(Font.font("Times New Roman", 20));
		Label L1 = new Label("Please enter the Scene ID and Artist name:");
		L1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(10);
		gid.getChildren().addAll(id1, id);
		VBox t = new VBox(25);
		t.getChildren().addAll(L1, gid);
		t.setPadding(new Insets(50, 0, 0, 50));
		t.setAlignment(Pos.TOP_LEFT);
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(30);
		se.setFitWidth(30);
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		Button find = new Button("Search", se);
		find.setFont(Font.font("Times New Roman", 15));
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.voice_actor where VIP = " + Integer.parseInt(id.getText());
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						q += "\nVoice actor ID: " + rs.getString(1) + " Voice actor salary: " + rs.getString(2)
								+ " Character name: " + rs.getString(3) + " Address" + rs.getString(4)
								+ " Phone number: " + rs.getString(5);
						t1.setText(q);
					}
				} else {
					t1.setText("Not found! Enter another ID");
				}
			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				VC(primaryStage);
			}
		});
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(retu);
		buttons.setPadding(new Insets(0, 0, 20, 58));
		VBox g1 = new VBox(25);
		g1.getChildren().addAll(find, t1);
		g1.setPadding(new Insets(0, 0, 20, 58));
		VBox g = new VBox(25);
		VBox g2 = new VBox(200);
		g.getChildren().addAll(t, g1);
		g2.getChildren().addAll(g, buttons);
		Scene s = new Scene(g2);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String d = "Writer Found!";

	public void searchcus(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Search for a writer");
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(0);
		Label id1 = new Label("Writer's ID");
		id1.setFont(Font.font("Times New Roman", 20));
		Label L1 = new Label("Please enter the writer's ID and name:");
		L1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(10);
		gid.getChildren().addAll(id1, id);
		VBox t = new VBox(25);
		t.getChildren().addAll(L1, gid);
		t.setPadding(new Insets(50, 0, 0, 50));
		t.setAlignment(Pos.TOP_LEFT);
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(30);
		se.setFitWidth(30);
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		Button find = new Button("Search", se);
		find.setFont(Font.font("Times New Roman", 15));
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.writer where Writer_ID = " + Integer.parseInt(id.getText());
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						d += "\nWriter ID: " + rs.getString(1) + " Writer salary: " + rs.getString(2) + " Writer name: "
								+ rs.getString(3);
						t1.setText(d);
					}
				} else {
					t1.setText("Not found! Enter another ID");
				}
			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Writer(primaryStage);
			}
		});
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(retu);
		buttons.setPadding(new Insets(0, 0, 20, 58));
		VBox g1 = new VBox(25);
		g1.getChildren().addAll(find, t1);
		g1.setPadding(new Insets(0, 0, 20, 58));
		VBox g = new VBox(25);
		VBox g2 = new VBox(200);
		g.getChildren().addAll(t, g1);
		g2.getChildren().addAll(g, buttons);
		Scene s = new Scene(g2);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String a = "Anime Found!";

	public void searchAnime(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Search for an anime");
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(0);
		Label id1 = new Label("Anime title ");
		id1.setFont(Font.font("Times New Roman", 20));
		Label L1 = new Label("Please enter the Anime writer name and Title:");
		L1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(10);
		gid.getChildren().addAll(id1, id);
		VBox t = new VBox(25);
		t.getChildren().addAll(L1, gid);
		t.setPadding(new Insets(50, 0, 0, 50));
		t.setAlignment(Pos.TOP_LEFT);
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(30);
		se.setFitWidth(30);
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		Button find = new Button("Search", se);
		find.setFont(Font.font("Times New Roman", 15));
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.anime where title = '" + id.getText() + "'";
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						a += "\nName Of writer: " + rs.getString(1) + " Title: " + rs.getString(2)
								+ " Copy right date: " + rs.getString(3);
						t1.setText(a);
					}
				} else {
					t1.setText("Not found! Enter another name");
				}
			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AnimeAdd(primaryStage);
			}
		});
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(retu);
		buttons.setPadding(new Insets(0, 0, 20, 58));
		VBox g1 = new VBox(25);
		g1.getChildren().addAll(find, t1);
		g1.setPadding(new Insets(0, 0, 20, 58));
		VBox g = new VBox(25);
		VBox g2 = new VBox(200);
		g.getChildren().addAll(t, g1);
		g2.getChildren().addAll(g, buttons);
		Scene s = new Scene(g2);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	String ar = "Artist Found!";

	public void searchArtist(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Search for an artist");
		TextField id = new TextField();
		id.setLayoutX(100);
		id.setLayoutY(0);
		Label id1 = new Label("Artist ID");
		id1.setFont(Font.font("Times New Roman", 20));
		Label L1 = new Label("Please enter the Artist ID:");
		L1.setFont(Font.font("Times New Roman", 20));
		HBox gid = new HBox(10);
		gid.getChildren().addAll(id1, id);
		VBox t = new VBox(25);
		t.getChildren().addAll(L1, gid);
		t.setPadding(new Insets(50, 0, 0, 50));
		t.setAlignment(Pos.TOP_LEFT);
		ImageView se = new ImageView(
				"https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/344/external-find-camping-icongeek26-outline-gradient-icongeek26.png");
		se.setFitHeight(30);
		se.setFitWidth(30);
		TextArea t1 = new TextArea();
		t1.setPrefWidth(250);
		t1.setPrefHeight(200);
		Button find = new Button("Search", se);
		find.setFont(Font.font("Times New Roman", 15));
		find.setOnAction(e -> {
			try {
				con.establishConnection();
				String SQL = "Select * from sys.artist where Artist_ID = " + Integer.parseInt(id.getText()) + "";
				ResultSet rs = con.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				if (columnsNumber != 0) {
					while (rs.next()) {
						ar += "\nArtist ID: " + rs.getString(1) + " Artist salary: " + rs.getString(2)
								+ " Artist name: " + rs.getString(3);
						t1.setText(ar);
					}
				} else {
					t1.setText("Not found! Enter another ID");
				}
			} catch (Exception e1) {
				t1.setText(e.toString());
			}
		});
		ImageView ret = new ImageView("https://img.icons8.com/nolan/344/return.png");
		ret.setFitHeight(30);
		ret.setFitWidth(30);
		Button retu = new Button("Return", ret);
		retu.setFont(Font.font("Times New Roman", 15));
		retu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Artist(primaryStage);
			}
		});
		HBox buttons = new HBox(20);
		buttons.getChildren().addAll(retu);
		buttons.setPadding(new Insets(0, 0, 20, 58));
		VBox g1 = new VBox(25);
		g1.getChildren().addAll(find, t1);
		g1.setPadding(new Insets(0, 0, 20, 58));
		VBox g = new VBox(25);
		VBox g2 = new VBox(200);
		g.getChildren().addAll(t, g1);
		g2.getChildren().addAll(g, buttons);
		Scene s = new Scene(g2);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	@SuppressWarnings("unchecked")
	private void WritertableView(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Writer");
		Label label = new Label("Writer Table");
		label.setFont(new Font("Times New Roman", 20));
		Writertable.setEditable(true);
		Writertable.setMaxHeight(800);
		Writertable.setMaxWidth(1190);
		TableColumn<Writer, Integer> WIDCol = new TableColumn<Writer, Integer>("Writer_ID");
		WIDCol.setMinWidth(396.333);
		WIDCol.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getWriter_ID()).asObject());
		WIDCol.setCellFactory(TextFieldTableCell.<Writer, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<Writer, Integer> WsalaryCol = new TableColumn<Writer, Integer>("Writer_Salary");
		WsalaryCol.setMinWidth(396.333);
		WsalaryCol.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getWriter_Salary()).asObject());
		WsalaryCol.setCellFactory(TextFieldTableCell.<Writer, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<Writer, String> WNameCol = new TableColumn<Writer, String>("Writer_Name");
		WNameCol.setMinWidth(396.333);
		WNameCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getWriter_Name()));
		WNameCol.setCellFactory(TextFieldTableCell.<Writer>forTableColumn());
		Writertable.setItems(WdataList);
		Writertable.getColumns().addAll(WIDCol, WsalaryCol, WNameCol);
	}

	@SuppressWarnings("unchecked")
	private void ArtisttableView(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Writer");
		Label label = new Label("Writer Table");
		label.setFont(new Font("Times New Roman", 20));
		Artisttable.setEditable(true);
		Artisttable.setMaxHeight(800);
		Artisttable.setMaxWidth(1190);
		TableColumn<Artist, Integer> WIDCol = new TableColumn<Artist, Integer>("artist_ID");
		WIDCol.setMinWidth(396.333);
		WIDCol.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getArtist_ID()).asObject());
		WIDCol.setCellFactory(TextFieldTableCell.<Artist, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<Artist, Integer> WsalaryCol = new TableColumn<Artist, Integer>("salary");
		WsalaryCol.setMinWidth(396.333);
		WsalaryCol.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getArtist_Salary()).asObject());
		WsalaryCol.setCellFactory(TextFieldTableCell.<Artist, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<Artist, String> WNameCol = new TableColumn<Artist, String>("name");
		WNameCol.setMinWidth(396.333);
		WNameCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getArtist_Name()));
		WNameCol.setCellFactory(TextFieldTableCell.<Artist>forTableColumn());
		Artisttable.getColumns().addAll(WIDCol, WNameCol, WsalaryCol);
		Artisttable.setItems(ArdataList);

	} 

	private void WritergetData() throws ClassNotFoundException, SQLException {
		String SQL;
		Wdata = new ArrayList<>();
		System.out.println("Connection established");
		SQL = "select * from sys.writer";
		con.establishConnection();
		ResultSet rs = con.executeQuery(SQL);
		while (rs.next())
			Wdata.add(new Writer(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)),
					(rs.getString(3))));
	}

	private void ArtistgetData() throws ClassNotFoundException, SQLException {
		String SQL;
		Ardata = new ArrayList<>();
		System.out.println("Connection established");
		SQL = "select * from sys.artist ";
		// con.establishConnection();
		con.closeConnection();
		ResultSet rs = con.executeQuery(SQL);
		while (rs.next()) {
			Ardata.add(new Artist(Integer.parseInt(rs.getString(1)), (rs.getString(2)),
					Integer.parseInt(rs.getString(3))));
		}
		// rs.close();
		// con.closeConnection();
	}

	private void ScenetableView(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Scene");
		Scenetable.setEditable(true);
		Scenetable.setMaxHeight(1000);
		Scenetable.setMaxWidth(2000);
		TableColumn<scene, Integer> Scene_IDCol = new TableColumn<scene, Integer>("Scene_ID");
		Scene_IDCol.setMinWidth(238);
		Scene_IDCol.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getScene_ID()).asObject());
		Scene_IDCol.setCellFactory(TextFieldTableCell.<scene, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<scene, String> SArtist_NameCol = new TableColumn<scene, String>("Artist_Name");
		SArtist_NameCol.setMinWidth(238);
		SArtist_NameCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getArtist_Name()));
		SArtist_NameCol.setCellFactory(TextFieldTableCell.<scene>forTableColumn());

		TableColumn<scene, Integer> PeriodCol = new TableColumn<scene, Integer>("Period");
		PeriodCol.setMinWidth(238);
		PeriodCol.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getPeriod()).asObject());
		PeriodCol.setCellFactory(TextFieldTableCell.<scene, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<scene, Integer> Start_TimeCol = new TableColumn<scene, Integer>("Start_Time");
		Start_TimeCol.setMinWidth(238);
		Start_TimeCol.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getStart_Time()).asObject());
		Start_TimeCol.setCellFactory(TextFieldTableCell.<scene, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<scene, Integer> EpisodeCol = new TableColumn<scene, Integer>("Episode");
		EpisodeCol.setMinWidth(238);
		EpisodeCol.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getEpisode()).asObject());
		EpisodeCol.setCellFactory(TextFieldTableCell.<scene, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<scene, Integer> Scene_IDCol1 = new TableColumn<scene, Integer>("artist_ID");
		Scene_IDCol1.setMinWidth(238);
		Scene_IDCol1.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getArtist_ID()).asObject());
		Scene_IDCol1.setCellFactory(TextFieldTableCell.<scene, Integer>forTableColumn(new IntegerStringConverter()));

		Scenetable.setItems(sdataList);
		Scenetable.getColumns().addAll(Scene_IDCol, SArtist_NameCol, PeriodCol, Start_TimeCol, EpisodeCol,
				Scene_IDCol1);
	}

	private void VoiceActortableView(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Scene");
		Scenetable.setEditable(true);
		Scenetable.setMaxHeight(800);
		Scenetable.setMaxWidth(1190);
		TableColumn<VoiceActor, Integer> Scene_IDCol = new TableColumn<VoiceActor, Integer>("Voice_Actor_ID");
		Scene_IDCol.setMinWidth(238);
		Scene_IDCol.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getVoice_Actor_ID()).asObject());
		Scene_IDCol
				.setCellFactory(TextFieldTableCell.<VoiceActor, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<VoiceActor, Integer> SArtist_NameCol = new TableColumn<VoiceActor, Integer>("Voice_Actor_Salary");
		SArtist_NameCol.setMinWidth(238);
		SArtist_NameCol
				.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getVoice_Actor_Salary()).asObject());
		SArtist_NameCol
				.setCellFactory(TextFieldTableCell.<VoiceActor, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<VoiceActor, String> PeriodCol = new TableColumn<VoiceActor, String>("Character_Name");
		PeriodCol.setMinWidth(238);
		PeriodCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCharacter_Name()));
		PeriodCol.setCellFactory(TextFieldTableCell.<VoiceActor>forTableColumn());

		TableColumn<VoiceActor, String> Start_TimeCol = new TableColumn<VoiceActor, String>("Address");
		Start_TimeCol.setMinWidth(238);
		Start_TimeCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getAddress()));
		Start_TimeCol.setCellFactory(TextFieldTableCell.<VoiceActor>forTableColumn());

		TableColumn<VoiceActor, Integer> EpisodeCol = new TableColumn<VoiceActor, Integer>("Phone_Number");
		EpisodeCol.setMinWidth(238);
		EpisodeCol.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getPhone_Number()).asObject());
		EpisodeCol.setCellFactory(TextFieldTableCell.<VoiceActor, Integer>forTableColumn(new IntegerStringConverter()));

		VoiceActortable.setItems(vcdataList);
		VoiceActortable.getColumns().addAll(Scene_IDCol, SArtist_NameCol, PeriodCol, Start_TimeCol, EpisodeCol);
	}

	private void ScenegetData() throws ClassNotFoundException, SQLException {
		String SQL;
		sdata = new ArrayList<>();
		System.out.println("Connection established");
		SQL = "select * from sys.scene";
		con.establishConnection();
		ResultSet rs = con.executeQuery(SQL);
		while (rs.next())
			sdata.add(new scene(Integer.parseInt(rs.getString(1)), rs.getString(2), Integer.parseInt(rs.getString(3)),
					Integer.parseInt(rs.getString(4)), Integer.parseInt(rs.getString(5)),
					Integer.parseInt(rs.getString(6))));
	}

	private void vcgetData() throws ClassNotFoundException, SQLException {
		String SQL;
		vcdata = new ArrayList<>();
		System.out.println("Connection established");
		SQL = "select * from sys.voice_actor";
		con.establishConnection();
		ResultSet rs = con.executeQuery(SQL);
		while (rs.next())
			vcdata.add(new VoiceActor(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)),
					rs.getString(3), rs.getString(4), Integer.parseInt(rs.getString(5))));
	}

	private void AnimegetData() throws ClassNotFoundException, SQLException {
		String SQL;
		Adata = new ArrayList<>();
		System.out.println("Connection established");
		SQL = "select * from sys.anime";
		con.establishConnection();
		ResultSet rs = con.executeQuery(SQL);
		while (rs.next())
			Adata.add(new Anime(rs.getString(1), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)),
					Integer.parseInt(rs.getString(5))));
	}

	@SuppressWarnings("unchecked")
	private void AnimetableView(Stage stage) {

		Scene scene = new Scene(new Group());
		stage.setTitle("Anime");

		Animetable.setEditable(true);
		Animetable.setMaxHeight(1000);
		Animetable.setMaxWidth(2000);

		TableColumn<Anime, String> Name_Of_WriterCol = new TableColumn<Anime, String>("name_of_writer");
		Name_Of_WriterCol.setMinWidth(238);
		Name_Of_WriterCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getName_Of_Writer()));
		Name_Of_WriterCol.setCellFactory(TextFieldTableCell.<Anime>forTableColumn());

		TableColumn<Anime, String> TitleCol = new TableColumn<Anime, String>("title");
		TitleCol.setMinWidth(238);
		TitleCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getTitle()));
		TitleCol.setCellFactory(TextFieldTableCell.<Anime>forTableColumn());

		TableColumn<Anime, String> Copy_Right_DateCol = new TableColumn<Anime, String>("copy right data");
		Copy_Right_DateCol.setMinWidth(238);
		Copy_Right_DateCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCopy_Right_Date()));
		Copy_Right_DateCol.setCellFactory(TextFieldTableCell.<Anime>forTableColumn());

		TableColumn<Anime, Integer> artist_ID = new TableColumn<Anime, Integer>("SID");
		artist_ID.setMinWidth(238);
		artist_ID.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getScene_ID()).asObject());
		artist_ID.setCellFactory(TextFieldTableCell.<Anime, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<Anime, Integer> Writer_ID = new TableColumn<Anime, Integer>("WID");
		Writer_ID.setMinWidth(238);
		Writer_ID.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getWriter_ID()).asObject());
		Writer_ID.setCellFactory(TextFieldTableCell.<Anime, Integer>forTableColumn(new IntegerStringConverter()));

		Animetable.setItems(AdataList);
		Animetable.getColumns().addAll(Name_Of_WriterCol, TitleCol, Copy_Right_DateCol, artist_ID, Writer_ID);

	}

	private void voicegetData() throws ClassNotFoundException, SQLException {
		String SQL;
		vdata = new ArrayList<>();
		System.out.println("Connection established");
		SQL = "select * from sys.voice";
		con.establishConnection();
		ResultSet rs = con.executeQuery(SQL);
		while (rs.next())
			vdata.add(new voice(rs.getString(1), Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3))));
	}

	private void voicetableView(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("voice");
		Label label = new Label("voice Table");
		label.setFont(new Font("Times New Roman", 20));
		voicetable.setEditable(true);
		voicetable.setMaxHeight(800);
		voicetable.setMaxWidth(1190);
		TableColumn<voice, String> WNameCol = new TableColumn<voice, String>("Titel");
		WNameCol.setMinWidth(238);
		WNameCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getTitel()));
		WNameCol.setCellFactory(TextFieldTableCell.<voice>forTableColumn());

		TableColumn<voice, Integer> WIDCol = new TableColumn<voice, Integer>("VIP");
		WIDCol.setMinWidth(238);
		WIDCol.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getVIP()).asObject());
		WIDCol.setCellFactory(TextFieldTableCell.<voice, Integer>forTableColumn(new IntegerStringConverter()));

		TableColumn<voice, Integer> WIDCol1 = new TableColumn<voice, Integer>("ID");
		WIDCol1.setMinWidth(238);
		WIDCol1.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getID()).asObject());
		WIDCol1.setCellFactory(TextFieldTableCell.<voice, Integer>forTableColumn(new IntegerStringConverter()));

		voicetable.setItems(vdataList);
		voicetable.getColumns().addAll(WIDCol, WNameCol, WIDCol1);
	}

	public static void main(String[] args) {
		launch(args);
	}

}


import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Game {
	
	final Shell shellGame;
	
	Label labelCell[][];
	
	Label labelCurScoreValue;
	
	Color white,gray,dark,dark_red,red,yellow,dark_yellow,blue,green;
	
	int cellValue[][];
	
	int currentScore ,bestScore;
	
	Game(final Shell shellMenu){
		
		shellGame = new Shell(Display.getCurrent());
		
		white = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
		gray = Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
		dark = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
		dark_red = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED);
		red = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		yellow = Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW);
		dark_yellow = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_YELLOW);
		blue = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		green = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		
		shellGame.setText("2048 Puzzle");
		shellGame.setBackground(white);
		shellGame.setSize(295,400);
		
		FormLayout formLayout = new FormLayout(); //раскладка компонентов
		shellGame.setLayout(formLayout);
		
		createWidgets(shellMenu);
		
		cellValue = new int [4][4];
		
		currentScore = 0;
		
		shellGame.addShellListener(new ShellAdapter(){ //открыть меню при закрытии
			public void shellClosed(ShellEvent e){     //данного окна 
				shellMenu.setVisible(true);
			}
		});
	}

	public void open(Shell shellMenu){
		shellGame.open();
		shellMenu.setVisible(false);
	}

	public void createWidgets(final Shell shellMenu){   //создание виджетов 
		
		Font font1 = new Font(Display.getCurrent(),"Arial",24,SWT.NORMAL);
		Font font2 = new Font(Display.getCurrent(),"Arial",10,SWT.NORMAL);
		Font font3 = new Font(Display.getCurrent(),"TimesNewRoman",18,SWT.BOLD);
		
		FormData formDataName = new FormData(); //расположение названия игры
		formDataName.top = new FormAttachment(3,0);
		formDataName.left = new FormAttachment(6,0);
		formDataName.right = new FormAttachment(34,0);
		formDataName.bottom = new FormAttachment(12,0);
		
		Label labelName = new Label(shellGame,SWT.CENTER);//label названия игры
		labelName.setFont(font1);                         //шрифт
		labelName.setText("2048");						  //помещаем текст
		labelName.setForeground(dark);					  //цвет заднего фона
		labelName.setLayoutData(formDataName);			  //размещаем label
		
		FormData formDataCurScoreT = new FormData();      //расположение label Score 
		formDataCurScoreT.left = new FormAttachment(44,0);
		formDataCurScoreT.top = new FormAttachment(labelName,0,SWT.TOP);
		formDataCurScoreT.right = new FormAttachment(64,0);
		formDataCurScoreT.bottom = new FormAttachment(8,0);
		
		Label labelCurScoreT = new Label(shellGame,SWT.CENTER);//label Score
		labelCurScoreT.setText("SCORE");
		labelCurScoreT.setFont(font2);
		labelCurScoreT.setBackground(gray);
		labelCurScoreT.setForeground(dark);				 //цвет текста
		labelCurScoreT.setLayoutData(formDataCurScoreT);
		
		FormData formDataCurScoreValue = new FormData(); //расположение значения набранных очков
		formDataCurScoreValue.left = new FormAttachment(44,0);
		formDataCurScoreValue.top = new FormAttachment(labelCurScoreT,0,SWT.BOTTOM);
		formDataCurScoreValue.right = new FormAttachment(64,0);
		formDataCurScoreValue.bottom = new FormAttachment(labelName,0,SWT.BOTTOM);
		
		labelCurScoreValue = new Label(shellGame,SWT.CENTER); //label набранные очки
		labelCurScoreValue.setBackground(gray);
		labelCurScoreValue.setForeground(dark);
		labelCurScoreValue.setLayoutData(formDataCurScoreValue);
		labelCurScoreValue.setText(Integer.toString(currentScore));
		
		FormData formDataBestScoreT = new FormData(); //расположение надписи Best
		formDataBestScoreT.left = new FormAttachment(73,0);
		formDataBestScoreT.top = new FormAttachment(labelCurScoreT,0,SWT.TOP);
		formDataBestScoreT.right = new FormAttachment(94,0);
		formDataBestScoreT.bottom = new FormAttachment(8,0);
		
		Label labelBestScoreT = new Label(shellGame,SWT.CENTER);//надпись Best
		labelBestScoreT.setText("BEST");
		labelBestScoreT.setFont(font2);
		labelBestScoreT.setBackground(gray);
		labelBestScoreT.setForeground(dark);
		labelBestScoreT.setLayoutData(formDataBestScoreT);
		
		FormData formDataBestScoreValue = new FormData();//расположение значения лучшего результата
		formDataBestScoreValue.left = new FormAttachment(73,0);
		formDataBestScoreValue.top = new FormAttachment(labelBestScoreT,0,SWT.BOTTOM);
		formDataBestScoreValue.right = new FormAttachment(94,0);
		formDataBestScoreValue.bottom = new FormAttachment(labelName,0,SWT.BOTTOM);
		
		Label labelBestScoreValue = new Label(shellGame,SWT.CENTER);//label лучший результат
		labelBestScoreValue.setBackground(gray);
		labelBestScoreValue.setLayoutData(formDataBestScoreValue);
		
		FormData formDataMainMenu = new FormData(); //расположение кнопки MainMenu
		formDataMainMenu.left = new FormAttachment(6,0);
		formDataMainMenu.top = new FormAttachment(16,0);
		formDataMainMenu.right = new FormAttachment(34,0);
		formDataMainMenu.bottom = new FormAttachment(24,0);

		Button buttonMainMenu = new Button(shellGame,SWT.PUSH);//кнопка MainMeny
		buttonMainMenu.setFont(font2);
		buttonMainMenu.setText("&Main menu");
		buttonMainMenu.setForeground(dark_red);
		buttonMainMenu.setLayoutData(formDataMainMenu);
		
		buttonMainMenu.addSelectionListener(new SelectionAdapter()// действие при нажатии на MainMenu
        {
            @Override public void widgetSelected(final SelectionEvent e)
            {
            		shellMenu.setVisible(true); //делаем видимым окно меню
            		shellGame.setVisible(false);//окно игрового поля скрываем          		
            }
        });
		
		FormData formDataRestart = new FormData();// расположение кнопки Restart
		formDataRestart.left = new FormAttachment(37,0);
		formDataRestart.top = new FormAttachment(buttonMainMenu,0,SWT.TOP);
		formDataRestart.right = new FormAttachment(65,0);
		formDataRestart.bottom = new FormAttachment(buttonMainMenu,0,SWT.BOTTOM);
		
		final Button buttonRestart = new Button(shellGame,SWT.PUSH);//кнопка Restart
		buttonRestart.setFont(font2);
		buttonRestart.setText("&Restart");
		buttonRestart.setForeground(dark_red);
		buttonRestart.setLayoutData(formDataRestart);
		
		buttonRestart.addSelectionListener(new SelectionAdapter()//действия при нажатии на Restart
        {
            @Override public void widgetSelected(final SelectionEvent e)
            {
        		for(int i = 0; i < 4; i++){  //обнуляем элементы массива
        			for(int j = 0 ; j < 4; j++){
        				cellValue[i][j] = 0;
        			}
        		}
        		
        		currentScore = 0; //значение набранных очков = 0
        		
        		setNumberInCell(0); // размещаем два числа на игровом поле
        		
        		updateField();// обновляем поле
            }
        });
		
		FormData [][] formDataCell= new FormData[4][4];//расположение игрового поля
		labelCell = new Label[4][4];				   
		
		for (int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				formDataCell[i][j] = new FormData();
				formDataCell[i][j].left = new FormAttachment(10 + 21*j,0);
				formDataCell[i][j].top = new FormAttachment(29 + 17*i,0);
				formDataCell[i][j].right = new FormAttachment(27 + 21*j,0);
				formDataCell[i][j].bottom = new FormAttachment(42 + 17*i,0);
				
				labelCell[i][j] = new Label(shellGame,SWT.CENTER);
				labelCell[i][j].setFont(font3);
				labelCell[i][j].setBackground(white);
				labelCell[i][j].setLayoutData(formDataCell[i][j]);
			}
		}
		
		FormData formDataField = new FormData();
		formDataField.left = new FormAttachment(6,0);
		formDataField.top = new FormAttachment(26,0);
		formDataField.right = new FormAttachment(labelBestScoreT,0,SWT.RIGHT);
		formDataField.bottom = new FormAttachment(96,0);
		
		Label labelField = new Label(shellGame,SWT.NONE);
		labelField.setBackground(gray);
		labelField.setLayoutData(formDataField);
	}
	
	public void play(){                          //обработчик нажатия клавиш
		
		Display.getCurrent().addFilter(SWT.KeyUp, new Listener(){
			
			public void handleEvent(Event e){
				
                if((e.keyCode == 16777220)){     //вправо
                    moveRight();
                    //checkEndGame();
                }
                
                if(e.keyCode == 16777219){		 //влево
        			moveLeft();
        			//checkEndGame();
				}
                
				if(e.keyCode == 16777218){		 //вниз
        			moveDown();
        			//checkEndGame();
				}
				
				if(e.keyCode == 16777217){ 	     //вверх
        			moveUp();
        			//checkEndGame();
				}
            }			
        });
	}
	
	public int getNumber(){                      //новое число(90% - 2, 10% - 4)
		
		int temp = 0;
	
		if((int)(Math.random()*100) >= 90){
			temp = 4;
		}
		else
			temp = 2;
		
		return temp;
	}

	public int getPosition(){				     //позиция(пустая клетка) для нового числа
		 
		ArrayList<Integer> emptyCell = new ArrayList<Integer>();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(cellValue[i][j] == 0){
					emptyCell.add(i*4+j);
				}
			}
		}

		return  emptyCell.get((int) (Math.random()*emptyCell.size()));
	}

	public void setNumberInCell(int turn){		 //заносим новое число в случайную пустую клетку
		 
		int posI,posJ,posF;
		
		if(turn == 0){
			
			posF = getPosition();
			posI = posF/4;
			posJ = posF%4;
			
			cellValue[posI][posJ] = getNumber();
		}
		
		posF = getPosition();
		
		posI = posF/4;
		posJ = posF%4;
		
		cellValue[posI][posJ] = getNumber();;
		
	}

	public void updateField(){                   //обновляем клетки(после сдвигов)
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(cellValue[i][j] != 0){
					if(cellValue[i][j]<8){
						labelCell[i][j].setBackground(white);
					}
					else if(cellValue[i][j]>=8 && cellValue[i][j]<64){
						labelCell[i][j].setBackground(yellow);
					}					
					else if(cellValue[i][j]>=64 && cellValue[i][j]<=256){
						labelCell[i][j].setBackground(red);
					}
					else if(cellValue[i][j]>256 && cellValue[i][j]<=1024){
						labelCell[i][j].setBackground(blue);
					}
					else if(cellValue[i][j]>=2048 && cellValue[i][j]<=4096){
						labelCell[i][j].setBackground(dark_yellow);
					}
					else if(cellValue[i][j]>4096){
						labelCell[i][j].setBackground(green);
					}
					
					labelCell[i][j].setText(Integer.toString(cellValue[i][j]));
				}
				else{
					labelCell[i][j].setText("");
					labelCell[i][j].setBackground(white);
				}
			}
		}
		
		labelCurScoreValue.setText(Integer.toString(currentScore));
	}
	
	public void moveUp(){						 //сдвиг снизу свверх
		
		boolean canMove = false;
		
		for (int j = 0; j < 4; j++)
		{
		    for (int i = 0; i < 4; i++)
		    {
		        for (int k = i + 1; k < 4; k++)
		        {
		            if (cellValue[k][j] != 0)
		            {
		                if (cellValue[i][j] == 0)
		                {
		                    cellValue[i][j] = cellValue[k][j];
		                    cellValue[k][j] = 0;
		                    canMove = true;
		                }
		                else
		                {
		                    if (cellValue[i][j] == cellValue[k][j])
		                    {
		                        cellValue[i][j] += cellValue[k][j];
		                        cellValue[k][j] = 0;
		                        currentScore += cellValue[i][j];
		                        canMove = true;
		                    }
		                    break;
		                }
		            }
		        }
		    }
		}

		if(canMove){
			setNumberInCell(1);
		}
		
		updateField();
	}
	
	public void moveDown(){                      //сдвиг сверху вниз
		
		boolean canMove = false;
		
		for (int j = 0; j < 4; j++)
		{
		    for (int i = 3; i >= 0; i--)
		    {
		        for (int k = i - 1; k >= 0; k--)
		        {
		            if (cellValue[k][j] != 0)
		            {
		                if (cellValue[i][j] == 0)
		                {
		                    cellValue[i][j] = cellValue[k][j];
		                    cellValue[k][j] = 0;
		                    canMove = true;
		                }
		                else
		                {
		                    if (cellValue[i][j] == cellValue[k][j])
		                    {
		                        cellValue[i][j] += cellValue[k][j];
		                        cellValue[k][j] = 0;
		                        currentScore += cellValue[i][j];
		                        canMove = true;
		                    }
		                    break;
		                }
		            }
		        }
		    }
		}
		
		if(canMove){
			setNumberInCell(1);
		}
		
		updateField();
	}

	public void moveLeft(){                      //сдвиг справа налево
		
		boolean canMove = false;
		
		for (int row = 0; row < 4; row++)
		{
			
		    int pivot = 0, col = 1;
		 
		    while (col < 4)
		    {
		        
		        if (cellValue[row][col] == 0)
		            col++;
		        
		        else if (cellValue[row][pivot] == 0)
		        {
		            cellValue[row][pivot] = cellValue[row][col];
		            cellValue[row][col++] = 0;
		            canMove = true;
		        }
		        
		        else if (cellValue[row][pivot] == cellValue[row][col])
		        {
		        	currentScore += cellValue[row][pivot]*2;
		            cellValue[row][pivot++] += cellValue[row][col];
		            cellValue[row][col++] = 0;		            
		            canMove = true;
		        }
		        
		        else if (++pivot == col)
		            col++;
		    }
		}
		
		if(canMove){
			setNumberInCell(1);
		}
		
		updateField();
	}

	public void moveRight(){                     //сдвиг слева направо
		
		boolean canMove = false;
		
		for (int row = 0; row < 4; row++)
		{
			
		    int pivot = 3, col = 2;
		 
		    while (col >= 0)
		    {
		        
		        if (cellValue[row][col] == 0)
		            col--;
		        
		        else if (cellValue[row][pivot] == 0)
		        {
		            cellValue[row][pivot] = cellValue[row][col];
		            cellValue[row][col--] = 0;
		            canMove = true;
		        }
		        
		        else if (cellValue[row][pivot] == cellValue[row][col])
		        {
		        	currentScore += cellValue[row][pivot]*2;
		            cellValue[row][pivot--] += cellValue[row][col];
		            cellValue[row][col--] = 0;
		            canMove = true;
		        }
				
		    	
		        
		        else if (--pivot == col)
		            col--;
		    }
		}

		if(canMove){
			setNumberInCell(1);
		}
		
		updateField();
	}

}		
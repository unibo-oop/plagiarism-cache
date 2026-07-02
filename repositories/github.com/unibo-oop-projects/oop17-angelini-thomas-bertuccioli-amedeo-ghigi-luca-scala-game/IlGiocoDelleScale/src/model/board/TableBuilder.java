package model.board;
import model.board.UdStrategy;;
public class TableBuilder implements Table {
	
	public final int tableheight;
	public final int tablewitdh;
	public final TableDifficulty difficulty;
	
	public TableBuilder(int tableheight, int tablewitdh, TableDifficulty difficulty) {
		this.tableheight = tableheight;
		this.tablewitdh = tablewitdh;
		this.difficulty = difficulty;
	}
		
	public TableBuilder CreateTable() {
		if (this.difficulty == TableDifficulty.FACILE) {
			for(int i=0; i<4; i++) {
				getObjectSnake(tableheight, tablewitdh);
			}
			for(int i=0; i<6; i++) {
				getObjectStairs(tableheight, tablewitdh);
			}
			
		}if(this.difficulty == TableDifficulty.MEDIA) {
				for(int i=0; i<6; i++) {
					getObjectSnake(tableheight, tablewitdh);
				}
				for(int i=0; i<6; i++) {
					getObjectStairs(tableheight, tablewitdh);
				}
			}else {
				for(int i=0; i<7; i++) {
					getObjectSnake(tableheight, tablewitdh);
				}
				for(int i=0; i<4; i++) {
					getObjectStairs(tableheight, tablewitdh);
				}
			}
		}
		return ;
		
	}


	
	
	

}

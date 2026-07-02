package menu.factories;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import menu.Board;
import util.Constants;
import util.Pair;
import util.Strings;
/**
 * A class that reads a file and make a leaderboard from that infos,
 * then write on the same file the leaderboard created.
*/
public class LeaderboardFactory {

	private List<Pair<Optional<String>, Integer>> leaderboardList = new ArrayList<>();
	private List<String> list = new ArrayList<>();
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	/**
	 * A method that reads a file and create a leaderboard from that,
	 * than write the leaderboard created on the same file.
	 * 
	 * @return a List of stings containing the leaderboard infos.
	 */
	public List<String> getLeaderboardList(Board board){
		
		try {
			this.bufferedReader = new BufferedReader(new FileReader(Strings.Leaderboard.LEADERBOARD_URI));
			//cycle to get the last score on the podium
			for(int i=Constants.LeaderboardConstants.minPodium; i<=Constants.LeaderboardConstants.maxPodium+1; i++) {
				Optional<String> position = Optional.ofNullable(bufferedReader.readLine());
				//if position is not a score then it will insert a standard string
				if(position.isEmpty() || position.get().equals("") || position.get().equals(Strings.Leaderboard.LEADERBOARD_DEFAULT_TEXT)) {
					position = Optional.of(Strings.Leaderboard.LEADERBOARD_DEFAULT_TEXT);
					this.leaderboardList.add(new Pair<>(position, 0));
				} else {
					//it takes the score from the line read
					String[] lineSplitted = position.get().split(" ");
					int lineScore = Integer.parseInt(lineSplitted[lineSplitted.length-1]);
					this.leaderboardList.add(new Pair<>(position, lineScore));
				}
				
				this.leaderboardList.sort(new Comparator<Pair<Optional<String>, Integer>>() {

					@Override
					public int compare(Pair<Optional<String>, Integer> o1,
							Pair<Optional<String>, Integer> o2) {
						return o2.getY() - o1.getY();
					}
				});
			}
			this.leaderboardList.remove(Constants.LeaderboardConstants.maxPodium);
			this.bufferedWriter = new BufferedWriter(new FileWriter(Strings.Leaderboard.LEADERBOARD_URI));
			//cycle that write the leaderboard in the file
			for(var line : this.leaderboardList) {
				String stringToWrite = line.getX().get();
				this.list.add(stringToWrite);
				this.bufferedWriter.write(stringToWrite);
				this.bufferedWriter.newLine();
			}
			this.bufferedWriter.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(board.getFrame(), "Can't find the leaderboard file", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return list;
	}
}

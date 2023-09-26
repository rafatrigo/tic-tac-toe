package main;

import classes.Subject;
import classes.Contest;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		//used to calculate how long the application took to complete
		double startTime = System.nanoTime(); 

		Subject best;

		int nSubjects = 100; //number of subjects for generation
		int generation = 0;

		int incompleteMatches; //matches that did not have a result
		int victoriesP1;

		Contest board = new Contest();

		List<Subject> population = new ArrayList<>();
		List<Subject> bests = new ArrayList<>();

		// generates the first generation
		for (int i = 0; i < nSubjects; i++) {
			Subject n = new Subject();

			population.add(n); //adds each Subject in the population
		}

		do {

			// make the subjects compete with each other
			for (int i = 0; i < nSubjects; i++) {
				for (int j = 0; j < nSubjects; j++) {
					board.compete(population.get(i), population.get(j));
				}
			}

			//order the subjects from best to worst
			do {

				int indexMelhor = 0;

				for (int i = 0; i < population.size(); i++) {
					double m = population.get(indexMelhor).getScore() / population.get(indexMelhor).getMatches();
					double n = population.get(i).getScore() / population.get(i).getMatches();

					if (m < n) {
						indexMelhor = i;
					}

				}
				
				//adds the currently best subject to the population of the best
				bests.add(population.get(indexMelhor));
				
				//removes the currently best subject from the population
				population.remove(indexMelhor);

			} while (!population.isEmpty());

			// makes a new population adding the first 50% of the bests
			for (int i = 0; i < nSubjects / 2; i++) {
				population.add(bests.get(i));
			}

			//crossover the current population to generate the other 50%
			for (int i = 0; i <= nSubjects - 1; i = i + 2) {

				Subject pai = bests.get(i);
				Subject mae = bests.get(i + 1);

				Subject n = new Subject(pai, mae);

				//add the new subjects to the population
				population.add(n);

			}

			// store the best of the generation
			best = bests.get(0);

			// clears the vector used to order the subjects
			bests.clear();
			victoriesP1 = board.getVictoriesP1();

			incompleteMatches = (nSubjects * nSubjects) - (board.getVictoriesP1() + board.getVictoriesP2() + board.getDraws());

			System.out.println("-----GERACAO " + generation + "-------");

			System.out.println("Vitorias P1: " + board.getVictoriesP1());
			System.out.println("Vitorias P2: " + board.getVictoriesP2());
			System.out.println("Empates: " + board.getDraws());
			System.out.println("Partidas indefinidas: " + incompleteMatches);

			generation++;
			
			board.setVictoriesP1(0);
			board.setVictoriesP2(0);
			board.setDraws(0);
		} while (victoriesP1 <= 7000);

		//used to calculate how long the application took to complete
		double endTime = System.nanoTime();
		
		System.out.println("Tempo de execussao: " + (endTime - startTime) / 1000000000);

		System.out.println();
		System.out.println();

		System.out.println("Melhor dados:");
		System.out.println("Partidas: " + best.getMatches());
		System.out.println("Vitorias: " + best.getVictories());
		System.out.println("Derrotas: " + best.getDraws());
		System.out.println("Empates: " + best.getDraws());
		System.out.println("Pontuacao: " + best.getScore());

		System.out.println();

		board.hCompete(best);

	}

}

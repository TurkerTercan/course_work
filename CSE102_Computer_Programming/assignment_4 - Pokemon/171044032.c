/*******************************************************************************************************************************************************/
/*				TURKER TERCAN		171044032		CSE 102			HW4					       */
/*******************************************************************************************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef enum attack{linear, quandrantic}attack_type;
typedef enum pokemons{ Charmander, Pikachu, Squirtle, Bulbasaur, Pidgeotto, Ratata, Mug, Caterpie, Zubat, Krabby}pokemon;

void oaks_laboratory(char Pokemon_name[10][11], pokemon Pokemons[], pokemon *my_Pokemons);
void show_Pokemons(char Pokemon_name[10][11], pokemon Pokemons[],int pokemon_count);
int compare( char name[11], char cmp[11]);
void catch_a_pokemon(char Pokemon_name[10][11], pokemon Pokemons[], pokemon *my_pocket);
int counter(int count, pokemon *my_pocket);
void release_a_pokemon(char Pokemon_name[10][11], pokemon Pokemons[],pokemon *my_pocket);
void edit_pocket( pokemon *my_pocket, int count, int i);
void battle(char Pokemon_name[10][11], int range[], attack_type type[], int attack_power[], int stamina[], pokemon user_Pokemons[]);
void show_area (char Pokemon_name[10][11],int area[8][8],int pokemon_staminas[4][4]);

//PART 1
void pokedex( char Pokemon_name[10][11], int range[], attack_type type[],int attack_power[], int stamina[]){
	int a;
	char name[11];
	printf("Please type name of the Pokémon (type exit to close Pokédex):\n");
	while( compare(name, "exit") != 1){			//It will checks the name and 'exit' are the same or not.
		scanf("%s", name);
		for( a = 0; a < 10; a++){
			if( compare(name, Pokemon_name[a]) == 1){		//When finds the pokemon, it prints its features.
				printf("Name\t:\t%s\n", Pokemon_name[a]);
				if( type[a] == linear){
					printf("A. Type\t:\tLinear\n");
				}
				else
					printf("A. Type\t:\tQuadrantic\n");
				printf("Range\t:\t%d block\n", range[a]);
				printf("A. Power:\t%d\n", attack_power[a]);
				printf("Stamina\t:\t%d\n", stamina[a]); 
			}
		}	
	}		
}
//My strcmp function
int compare( char name[11], char cmp[11] ){
	int i;
	for( i = 0; i < 11; i++){
		if( name[i] != cmp[i]){
			return 0;
		}
		if( name[i] == '\0' && cmp[i] == '\0'){		//When the arrays come to an end, for loop goint to be break.
			break;
		}
	}
	return 1;
}

//PART 2
void oaks_laboratory(char Pokemon_name[10][11], pokemon Pokemons[], pokemon *my_Pokemons){
	int i;
	int pokemon_count = 10;

	while( i != 5 ){
		printf("Welcome to Laboratory of Professor Oak. How can I help you?\n");
		printf("  1) Show Pokémons\n");
		printf("  2) Catch a Pokémon\n");
		printf("  3) Release a Pokémon\n");
		printf("  4) Show my pocket\n");
		printf("  5) Back\n");
		scanf("%d", &i);
		switch( i ){
			case 1:	show_Pokemons( Pokemon_name, Pokemons, pokemon_count); break;
			case 2:	catch_a_pokemon( Pokemon_name, Pokemons, my_Pokemons); break;
			case 3:	release_a_pokemon( Pokemon_name, Pokemons, my_Pokemons);	break;
			case 4:	pokemon_count = counter(pokemon_count, my_Pokemons); show_Pokemons(Pokemon_name, my_Pokemons, pokemon_count); pokemon_count = 10; break;
			default: 	break;
		}
	}	
}

void show_Pokemons(char Pokemon_name[10][11], pokemon Pokemons[],int pokemon_count){
	int i;
	int a;
	printf("\n");
	for( i = 0 ; i < pokemon_count ; i++){
		a = Pokemons[i];
		printf("%d - %s\n", i, Pokemon_name[a]);
	}
}
void catch_a_pokemon(char Pokemon_name[10][11], pokemon Pokemons[], pokemon *my_pocket){
	int i;
	int a;
	int count = 0;
	count = counter(count, my_pocket);
	show_Pokemons( Pokemon_name, Pokemons, 10 ); //Shows all the pokemons.
	printf("Which Pokémon do you want to catch ?\n");
	printf("You have %d pokémon(s) in your pocket. You can't carry more than 4.\n", count);
	if( count != 4 ){
		while( i < 0 || i > 10){
			scanf("%d", &i);
			if( i < 0 || i > 10 ){
				printf("Wrong Choice. Try Again!\n");
			}
			my_pocket[count] = i; //Choosen pokemon will be in the pokeballs[].
			count++;
		}
	}
}
//I created this function for count value.
int counter(int count, pokemon *my_pocket){
	int a;
	for( a = 0; a < 4 ; a++){
		if(my_pocket[a] < 0 || my_pocket[a] > 10){
			return a;
		}
	}
	return 4;
}

void release_a_pokemon(char Pokemon_name[10][11], pokemon Pokemons[],pokemon *my_pocket){
	int i;
	int x;
	pokemon temp;
	int count = counter(count, my_pocket);
	show_Pokemons(Pokemon_name, my_pocket, count);
	if( count != 0){
		printf("Which pokémon do you want to release ?\n");
		scanf("%d", &i);
		while( i < 0 || i > count){
			printf("Wrong Choice! Try Again.\n");
			scanf("%d", &i);
		}
		edit_pocket(my_pocket, count, i);
	}
	else{
		printf("Your pocket is empty.\n");
	}

}	

void edit_pocket( pokemon *my_pocket, int count, int i){
//Assigns a value of -1 to the last pokemon after overwriting the pokemons that follows the selected pokemon.
	int x;
	for( x = 0 ; x < count; x++){
		if( x == count - 1){
			my_pocket[count - 1] = -1;
		}
		else
			my_pocket[i+x] = my_pocket[i+x+1];
	}
}


//PART 2 ENDING

//PART 3
void battle(char Pokemon_name[10][11], int range[10], attack_type type[10], int attack_power[10], int stamina[10], pokemon user_Pokemons[]){
	
	int number_pokemons;
	char state[15];
	int area[8][8] = { {-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1}};
	int staminas[4][4] = {{-1,-1,-1,-1},{0,0,0,0},{-1,-1,-1,-1},{0,0,0,0}};
	int your_stamina = 4, enemy_stamina = 4;
	int i, a = 0, b = 0, x = -1, y = -1, enemyx[4], enemyy[4],t, ally_x[4], ally_y[4], ally_xrange[4], ally_yrange[4];
	int j, k, z,value, enemy_rangex[4], enemy_rangey[4],min1 = 0,min2 = 0;
	//Calculates how many pokemons the user has caught.
	srand(time(NULL));
	for( number_pokemons = 0; number_pokemons < 4 ; number_pokemons++){
		if(user_Pokemons[number_pokemons] == -1){
			break;
		}
	}
	printf("\n\t\tWELCOME TO POKEMON TOURNAMENT\n\n");
	if( number_pokemons == 0){
		printf("There is no pokémon in your pocket. You should go to the lab and catch few pokemons first. \n");
	}
	else {
		printf("1ST Rule: You can locate your pokémons on the first two row of the area\n");
		printf("2ND Rule: The tournament area is 8x8 units in size.\n");
		printf("(So you and your enemy can not cross the limits of the game.)\n");
		printf("3RD Rule: This is a turned-based game tournament\n");
		printf("(After you played, the enemy will have right to move and attack. Be carefull!)\n");
		printf("4TH Rule:At the start of each turn, you will able to choose a pokemon and move it\n");
		printf("1 or 2 blocks linearly. After that, your pokemon attack automaticlly if there\n");
		printf("any pokemon within its range. According to pokemon's type pokemon will attack\n");
		printf("linear or diagonal and damages the enemy pokemon as its attack power.\n");
		printf("5TH Rule: If one side does not have any pokémon, the tournament ends.\n");
		printf("\n\tTutorial is ended. Are you READY (YES/NO)?\n");
		scanf("%s", state);
		if( compare(state,"yes") == 1 || compare(state,"Yes") == 1 || compare(state,"YES") == 1){
			printf("\nGOOD LUCK!\n");
			while( number_pokemons != 0){
				printf("\nWhich pokémon do you want to locate ? If you don't want to, just type -1.\n");
				show_Pokemons(Pokemon_name, user_Pokemons, number_pokemons);
				scanf("%d", &i);
				if( i == -1){
					break;
				}
				printf("Select Column (MAX = 8 MIN = 1): ");
				while( x < 1 || x > 8 ){
					scanf("%d", &x);
					if( x < 1 || x > 8 ){
						printf("Error: Select a location within the borders (between 1 and 8).\n");
					}
				}
				x = x - 1;
				printf("Select Row (MAX = 2 MIN = 1): ");
				while( y < 1 || y > 2 ){
					scanf("%d", &y);
					if( y < 1 || y > 2 ){
						printf("Error: Select a location within the borders (between 1 and 2).\n");
					}
				}
				y = y - 1;
				if( area[y][x] < 10 && area[y][x] >= 0){
					printf("This location is already taken. Please choose another.\n");
				}
				else{
					area[y][x] = user_Pokemons[i];
					staminas[0][a] = user_Pokemons[i];
					staminas[1][a] = stamina[user_Pokemons[i]];
					edit_pocket(user_Pokemons, number_pokemons, i); //Not able to choose the same pokemon
					number_pokemons--;
					a++;
				}
				x = -1;	//These two need to enter the while loops.
				y = -1;	
			}
			//AI's pokemons' locations
			if( i != 1)	{
				for( a = 0; a < 4; a++){
					while(1){
						i = (rand() % 10) + 10; 	//Choose a random pokemon. To not the mix the enemy and ally pokemons, i add i int 10 so when printing it'll be easier to detect.
						for( y = 0 ; y < 8 ; y++){
							for( x = 0; x < 8 ; x++){
								if( i == area[x][y]){
									i = (rand() % 10) + 10; 
									break;
								}
							}
						}
						if( i != area[x][y]){
							break;
						}
					}
					x = rand() % 8; 	//between 0 and 7
					y = (rand() % 2) + 6; //between 5 and 7
					if( area[y][x] < 10 && area[y][x] >= 0){
						a--; //Because it is already taken
					}
					else if( area[y][x] < 20 && area[y][x] >= 10){
						a--; //Because it is already taken
					}
					else{
						area[y][x] = i;
						staminas[2][a] = i;
						i = i - 10;
						staminas[3][a] = stamina[i];
					}		
				}
				show_area( Pokemon_name, area, staminas);
			}
			
			for( a = 0 ; a < 4 ; a++){
				if( staminas[0][a] < 10 && staminas[0][a] >= 0){
					your_stamina++;
				}
			}

			a = 0;
			b = 0;
			printf("\n 		THE BATTLE HAS BEGUN\n");
			while( your_stamina != 0 && enemy_stamina != 0 ){
				enemy_stamina = 4;
				your_stamina = 4;
				for( a = 0; a < 4 ; a++){	//Checks the pokemons both sides and decreases the staminas.
					if( staminas[2][a] == -1){
						enemy_stamina--;
					}
					if( staminas[0][a] == -1){
						your_stamina--;
					}
				}
				if( enemy_stamina == 0 || your_stamina == 0){
					break;
				}
				a = 0;
				b = 0;
				for( number_pokemons = 0; number_pokemons < 4 ; number_pokemons++){
					if(staminas[0][number_pokemons] == -1){
						break;
					}
					else
						user_Pokemons[number_pokemons] = staminas[0][number_pokemons];
				}

				show_Pokemons(Pokemon_name, user_Pokemons, number_pokemons);
				printf("Choose a pokemon to move: ");
				while(1){
					scanf("%d", &i);
					if( i < 0 || i >= number_pokemons ){
						printf("Wrong Choice. Try Again!\n");
					}
					else
						break;
				}
				for( y = 0 ; y < 8 ; y++){
					for( x = 0 ; x < 8 ; x++){
						if( area[y][x] == staminas[0][i]){
							area[y][x] = -1;
							j = y;
							k = x;
							break;
						}
					}
				}
				//Movement
				printf("Warning: If you cross the borders or go to place where a enemy pokemon stays there, your move will not be executed.\n");
				printf("How many blocks do you want to go (1 or 2) ?\n");
				while( b != 1 && b != 2){
					scanf("%d", &b);
					if (b != 1 && b != 2){
						printf("Please type 1 or 2\n");
					}
				}	
				printf("Which way you want to go ?\n Left: 4  Right: 6  Top: 8  Down: 2\n");
				while( a != 2 && a != 4 && a != 6 && a != 8){
					scanf("%d", &a);
					switch(a){
						case 2: if( j + b <= 7 && area[j+b][k] == -1){
						 j = j + b;
						}
						else
							printf("Error: Your choice was not valid. \n"); break;
						case 4: if( k >= b && area[j][k - b] == -1){
						 k = k - b;
						}
						else
							printf("Error: Your choice was not valid. \n"); break;
						case 6: if( k + b <= 7 && area[j][k + b] == -1){
						 k = k + b;
						}
						else
							printf("Error: Your choice was not valid. \n"); break;
						case 8: if( j >= b && area[j-b][k] == -1){
						 j = j - b;
						}
						else
							printf("Error: Your choice was not valid,\n"); break;
						default: printf("Wrong Choice! Try Again.\n"); break;
					}
				}
				if( area[j][k] == -1){			//It is not overwriting 2 pokemons
					area[j][k] = staminas[0][i];
				}
				else
					area[x][y] = staminas[0][i];

				value = staminas[0][i];
				a = 0;
				b = 0;
				t = 0;
				
				if( type[value] == 0 ){
					//Range Check
					//X- Axis
					for( x = k - range[value] ; x <= k + range[value]; x++){
						if( area[j][x] >= 10 && area[j][x] < 20){
							enemyx[t] = area[j][x];
							if( x < k){
								enemy_rangex[t] = k - x;	//It keeps ranges.
								t++;
							}
							else if( k < x){
								enemy_rangex[t] = x - k;
								t++;
							}		
							
						}
					}
					//Y - Axis
					for( y = j - range[value] ; y <= j + range[value]; y++){
						if( area[y][k] >= 10 && area[y][k] < 20){
							enemyy[a] = area[y][k];
							if( y < j){
								enemy_rangey[a] = j - y;
								a++;
							}
							else if( j < y){
								enemy_rangey[a] = y - j;
								a++;
							}
						}
					}
					//Choose the closest range or ranges
					min1 = enemy_rangex[0];
					for( x = 0; x < t ; x++){
						if( min1 > enemy_rangex[x]){
							min1 = enemy_rangex[x];
						}
					}
					min2 = enemy_rangey[0];
					for( x = 0; x < a; x++){
						if(min2 > enemy_rangey[x]){
							min2 = enemy_rangey[x];
						}
					}
					if( t == 0){
						min1 =0;
					}
					if( a ==0 ){
						min2 =0;
					}

					if( min1 != 0 && min2 != 0){
						if( min1 > min2 ){
							min1 = min2;
						}
					}
					else if( min1 == 0 && min2 != 0){
						min1 = min2;
					}
					z = a;
					//Attack
					for( x = 0 ; x < t; x++){
						if( enemy_rangex[x] == min1){
							for( a = 0; a < 4; a++){
								if( enemyx[x] == staminas[2][a]){	//If it is minimum range. It'll enter the loop and attacks.
									enemyx[x] = -1;
									enemy_rangex[x] = 7;
									if( staminas[3][a] > 0 ){
										staminas[3][a] = staminas[3][a] - attack_power[staminas[0][i]];
										printf("%s attacked to %s and damaged %d\n", Pokemon_name[staminas[0][i]], Pokemon_name[staminas[2][a] - 10], attack_power[staminas[0][i]]);
										if(staminas[3][a] <= 0){
											staminas[3][a] = 0;
											for(int p = 0 ; p < 8; p++){
												for(int l = 0 ; l < 8; l++){
													if( area[p][l] == staminas[2][a]){
														area[p][l] = -1;
														staminas[2][a] = -1;
													
													}
												}
											}
										}
									}	
								}
							}
						}
					}
					for( x = 0 ; x < z; x++){
						if( enemy_rangey[x] == min1){
							for( a = 0; a < 4; a++){
								if( enemyy[x] == staminas[2][a]){ //If it is minimum range. It'll enter the loop and attacks.
									enemyy[x] = -1;
									enemy_rangey[x] = 7;
									//Next time it will not able to enter this loop.
									if( staminas[3][a] > 0 ){
										staminas[3][a] = staminas[3][a] - attack_power[staminas[0][i]];
										printf("%s attacked to %s and damaged %d\n", Pokemon_name[staminas[0][i]], Pokemon_name[staminas[2][a] - 10], attack_power[staminas[0][i]]);
										if(staminas[3][a] <= 0){
											staminas[3][a] = 0;
											for(int p = 0 ; p < 8; p++){
												for(int l = 0 ; l < 8; l++){
													if( area[p][l] == staminas[2][a]){
														area[p][l] = -1;
														staminas[2][a] = -1;
													
													}
												}
											}
										}
									}
								}
							}
						}
					}
					a = 0;
					b = 0;
					t = 0;
					z = 0;
					min1 = 0 , min2 = 0;
				}
				//Quadrantic
				else if( type[value] == 1 ){
					//Range Check
					y = j - range[value];
					for( x = k - range[value] ; x <= k + range[value]; x++, y++){	//Diagonal ranges are checking.
						if( area[y][x] >= 10 && area[y][x] < 20){
							enemyx[t] = area[y][x];		
							t++;
						}
					}
					y = j + range[value];
					for( x = k - range[value] ; x <= k + range[value]; x++, y--){
						if( area[y][x] >= 10 && area[y][x] < 20){
							enemyx[t] = area[y][x];		
							t++;
						}
					}
					//Attack
					for( x = 0 ; x < t; x++){
						for(a = 0 ; a < 4; a++){
							if( enemyx[x] == staminas[2][a]){ //If it is minimum range. It'll enter the loop and attacks.
								enemyx[x] = -1;
								if( staminas[3][a] > 0 ){
										staminas[3][a] = staminas[3][a] - attack_power[staminas[0][i]];
										printf("%s attacked to %s and damaged %d\n", Pokemon_name[staminas[0][i]], Pokemon_name[staminas[2][a] - 10], attack_power[staminas[0][i]]);
										if(staminas[3][a] <= 0){
											staminas[3][a] = 0;
											for(int p = 0 ; p < 8; p++){
												for(int l = 0 ; l < 8; l++){
													if( area[p][l] == staminas[2][a]){
														area[p][l] = -1;
														staminas[2][a] = -1;
													
													}
											}
										}
									}
								}
							}
						}
					}
				}

				
				show_area( Pokemon_name, area, staminas);
				enemy_stamina = 4;
				your_stamina = 4;
				for( a = 0; a < 4 ; a++){
					if( staminas[2][a] == -1){
						enemy_stamina--;
					}
					if( staminas[0][a] == -1){
						your_stamina--;
					}
				}
				if( enemy_stamina == 0 || your_stamina == 0){
					break;
				}
				//AI moves
				i = rand() % 4;
				while( staminas[2][i] == -1){
					i = rand() % 4;
				}
				for( y = 0 ; y < 8 ; y++){
					for( x = 0 ; x < 8 ; x++){
						if( area[y][x] == staminas[2][i]){
							area[y][x] = -1;
							j = y;
							k = x;
							break;
						}
					}
				}
				printf("AI's turn\n");
				a = rand() % 8;
				do{ switch( a ){
					case 0: if( j + 2 <= 7 && area[j+2][k] == -1 ){	//It randomly types a number and moves according to it.
						j = j + 2;
						break;
						}
					case 1: if( j + 1 <= 7 && area[j+1][k] == -1){
						j = j + 1;
						break;
					}
					case 2: if( j - 2 >= 0 && area[j-2][k] == -1){
						j = j - 2;
							break;
					}
					case 3: if( j - 1 >= 0 && area[j-1][k] == -1){
						j = j - 1;
						break;
					}
					case 4: if( k + 2 <= 7 && area[j][k+2] == -1){
						k = k + 2;
						break;
					}
					case 5: if( k + 1 <= 7 && area[j][k+1] == -1){
						k = k + 1;
						break;
					}
					case 6: if( k - 2 >= 0 && area[j][k-2] == -1){
						k = k - 2;
						break;
					}
					case 7: if( k - 1 >= 0 && area[j][k-1] == -1){
						k = k - 1;
						break;
					}
						default: break;
				}}while( k == x && y == j);
				if( area[j][k] == -1){			//It is not overwriting 2 pokemons
					area[j][k] = staminas[2][i];
				}
				else
					area[x][y] = staminas[2][i];

				//Enemy Attack
				value = staminas[2][i] - 10;
				a = 0;
				b = 0;
				t = 0;
				
				if( type[value] == 0 ){
					//Range Check
					//X- Axis
					for( x = k - range[value] ; x <= k + range[value]; x++){
						if( area[j][x] >= 0 && area[j][x] < 10){
							ally_x[t] = area[j][x];
							if( x < k){
								ally_xrange[t] = k - x;
								t++;
							}
							else if( k < x){
								ally_xrange[t] = x - k;
								t++;
							}		
							
						}
					}
					//Y - Axis
					for( y = j - range[value] ; y <= j + range[value]; y++){
						if( area[y][k] >= 0 && area[y][k] < 10){
							ally_y[a] = area[y][k];
							if( y < j){
								ally_yrange[a] = j - y;
								a++;
							}
							else if( j < y){
								ally_yrange[a] = y - j;
								a++;
							}
						}
					}
					//Choose the closest range or ranges
					min1 = ally_xrange[0];
					for( x = 0; x < t ; x++){
						if( min1 > ally_xrange[x]){
							min1 = ally_xrange[x];
						}
					}
					min2 = ally_yrange[0];
					for( x = 0; x < a; x++){
						if(min2 > ally_yrange[x]){
							min2 = ally_yrange[x];
						}
					}
					if( t == 0){
						min1 =0;
					}
					if( a ==0 ){
						min2 =0;
					}

					if( min1 != 0 && min2 != 0){
						if( min1 > min2 ){
							min1 = min2;
						}
					}
					else if( min1 == 0 && min2 != 0){
						min1 = min2;
					}
					z = a;
					//Attack
					for( x = 0 ; x < t; x++){
						if( ally_xrange[x] == min1){
							for( a = 0; a < 4; a++){
								if( ally_x[x] == staminas[0][a]){
									ally_x[x] = -1;
									ally_xrange[x] = 7;
									if( staminas[1][a] > 0 ){
										staminas[1][a] = staminas[1][a] - attack_power[staminas[2][i] - 10];
										printf("%s attacked to %s and damaged %d\n", Pokemon_name[staminas[2][i] - 10], Pokemon_name[staminas[0][a]], attack_power[staminas[2][i] - 10]);
										if(staminas[1][a] <= 0){
											staminas[1][a] = 0;
											for(int p = 0 ; p < 8; p++){
												for(int l = 0 ; l < 8; l++){
													if( area[p][l] == staminas[0][a]){
														area[p][l] = -1;
														staminas[0][a] = -1;
													
													}
												}
											}
										}
									}	
								}
							}
						}
					}
					for( x = 0 ; x < z; x++){
						if( ally_yrange[x] == min1){
							for( a = 0; a < 4; a++){
								if( ally_y[x] == staminas[0][a]){
									ally_y[x] = -1;
									ally_yrange[x] = 7;
									//Next time it will not able to enter this loop.
									if( staminas[1][a] > 0 ){
										staminas[1][a] = staminas[1][a] - attack_power[staminas[2][i] - 10];
										printf("%s attacked to %s and damaged %d\n", Pokemon_name[staminas[2][i] - 10], Pokemon_name[staminas[0][a]], attack_power[staminas[2][i] - 10]);
										if(staminas[1][a] <= 0){
											staminas[1][a] = 0;
											for(int p = 0 ; p < 8; p++){
												for(int l = 0 ; l < 8; l++){
													if( area[p][l] == staminas[0][a]){
														area[p][l] = -1;
														staminas[0][a] = -1;
													
													}
												}
											}
										}
									}
								}
							}
						}
					}
					a = 0;
					b = 0;
					t = 0;
					z = 0;
					min1 = 0 , min2 = 0;
				}
				//Quadrantic
				else if( type[value] == 1 ){
					//Range Check
					y = j - range[value];
					for( x = k - range[value] ; x <= k + range[value]; x++, y++){
						if( area[y][x] >= 0 && area[y][x] < 10){
							ally_x[t] = area[y][x];		
							t++;
						}
					}
					y = j + range[value];
					for( x = k - range[value] ; x <= k + range[value]; x++, y--){
						if( area[y][x] >= 0 && area[y][x] < 10){
							ally_x[t] = area[y][x];		
							t++;
						}
					}
					//Attack
					for( x = 0 ; x < t; x++){
						for(a = 0 ; a < 4; a++){
							if( ally_x[x] == staminas[2][a]){
								ally_x[x] = -1;
								if( staminas[1][a] > 0 ){
										staminas[1][a] = staminas[1][a] - attack_power[staminas[2][i] - 10];
										printf("%s attacked to %s and damaged %d\n", Pokemon_name[staminas[2][i] - 10], Pokemon_name[staminas[0][a]], attack_power[staminas[2][i] - 10]);
										if(staminas[1][a] <= 0){
											staminas[1][a] = 0;
											for(int p = 0 ; p < 8; p++){
												for(int l = 0 ; l < 8; l++){
													if( area[p][l] == staminas[0][a]){
														area[p][l] = -1;
														staminas[0][a] = -1;
													
													}
											}
										}
									}
								}
							}
						}
					}
				}
				
				show_area( Pokemon_name, area, staminas);
				enemy_stamina = 4;
				your_stamina = 4;
				for( a = 0; a < 4 ; a++){
					if( staminas[2][a] == -1){
						enemy_stamina--;
					}
					if( staminas[0][a] == -1){
						your_stamina--;
					}
				}
				if( enemy_stamina == 0 || your_stamina == 0){
					break;
				}
				for( a = 0 ; a < 4 ; a++){
					ally_x[a] = -1;
					ally_y[a] = -1;
					ally_xrange[a] = 7;
					ally_yrange[a] = 7;
					enemyx[a] = -1;
					enemyy[a] = -1;
					enemy_rangex[a] = 7;
					enemy_rangey[a] = 7;
				}
				a = 0;
				b = 0;
				t = 0;
				z = 0;
				min1 = 0;
				min2 = 0;
				x = -1;
				y = -1;
				j = -1;
				k = -1;

			}
			if( enemy_stamina == 0){
				printf("\n\tYOU WON!\n");
			}
			else if( your_stamina == 0){
				printf("\n\tYOU LOST!\n");
			}
		}
	}
}

void show_area (char Pokemon_name[10][11],int area[8][8],int pokemon_staminas[4][4]){
	int x,y,z,v,i,j;
	for( x = 0 ; x < 8; x++){
			printf("--------");
		}
	printf("-\n");
	for( y = 0 ; y < 8; y++){
		for( x = 0 ; x < 8; x++){
			printf("|");
			if( area[y][x] < 10 && area[y][x] >= 0){
				i = area[y][x];
				printf("   %c%c  ", Pokemon_name[i][0], Pokemon_name[i][1]);
			}
			else if(area[y][x] >= 10 && area[y][x] < 20){
				i = area[y][x] - 10;
				printf("   %c%c  ", Pokemon_name[i][0], Pokemon_name[i][1]);
			}
			else
				printf("\t");
		}
		printf("|\n");
		for( x = 0 ; x < 8; x++){
			printf("|");
			if( area[y][x] < 10 && area[y][x] >= 0){
				i = area[y][x];
				for( z = 0; z < 4; z++){
					if( i == pokemon_staminas[0][z]){
						if( pokemon_staminas[1][z] >= 1000)
							printf(" (%d)", pokemon_staminas[1][z]);
						else if( pokemon_staminas[1][z] < 1000)
							printf(" (%d) ", pokemon_staminas[1][z]);
						else if( pokemon_staminas[1][z] < 100)
							printf(" (%d)   ", pokemon_staminas[1][z]);

						break;
					}
				}
			}
			else if( area[y][x] < 20 && area[y][x] >= 10){
				i = area[y][x];
				for( z = 0; z < 4; z++){
					if( i == pokemon_staminas[2][z]){
						if( pokemon_staminas[3][z] >= 1000)
							printf(" (%d)", pokemon_staminas[3][z]);
						else if( pokemon_staminas[3][z] < 1000)
							printf(" (%d) ", pokemon_staminas[3][z]);
						else if( pokemon_staminas[3][z] < 100)
							printf(" (%d)   ", pokemon_staminas[3][z]);

						break;
					}
				}
			}
			else
				printf("\t");
		}
		printf("|\n");
		for( x = 0 ; x < 8; x++){
			printf("--------");
		}
		printf("-\n");	
	}
}

int main(){
	
	char Pokemon_name[10][11] = { "Charmander", "Pikachu", "Squirtle", "Bulbasaur", "Pidgeotto", "Ratata", "Mug", "Caterpie", "Zubat", "Krabby"};
	int range[10] = { 1,3,4,3,2,2,1,2,3,2};
	pokemon Pokemons[10] = {0,1,2,3,4,5,6,7,8,9};
	pokemon pokeballs[4] = {-1,-1,-1,-1}; 	//Just making sure pokeballs aren't in any pokemon value.
	pokemon *my_Pokemons = pokeballs; 		//If i play with my_Pokemons, also pokeballs will change according to it.
	int i;
	attack_type type[10] = { 1, 0, 0, 0, 1, 0, 1, 1, 0, 0}; //Pokemons' features.
	int attack_power[10] = {500, 350, 300, 400, 250, 250, 350, 200, 350, 300}; 
	int stamina[10] = {2200, 1500, 1700, 2500, 1900, 2500, 3000, 1200, 1250, 2600};
	while( i != 4){
		printf("Please select an option to continue:\n");
		printf("1) Open Pokédex\n");
		printf("2) Go to Oak’s Laboratory\n");
		printf("3) Enter the tournament\n");
		printf("4) Exit\n");
		scanf("%d", &i);
		switch( i ){
			case 1: pokedex( Pokemon_name, range, type, attack_power, stamina); break;
			case 2: oaks_laboratory( Pokemon_name, Pokemons, my_Pokemons); break;
			case 3: battle(Pokemon_name, range, type, attack_power, stamina, pokeballs); break;
			case 4: return 0; break;
			default: printf("Wrong Choice! Try Again."); break;
		}
	}
	return 0;
}

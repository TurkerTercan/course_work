//		TURKER TERCAN		1710444032		CSE 102			HW7		//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>


typedef enum players { noone, cap, car} player_type;

typedef enum blocks { start, property, tax, punish} block_type;

struct player
{
	player_type type; //Holds type of the player. The player_type must be defined as enumerated type which consist of noone, cap and car values.
	int current_block_id; //Holds player location as block id.
	int owned_block_ids[11]; //Holds property block ids that are owned by the user.
	int account; //Holds amount of the current money of the player
	int turn_to_wait; //Holds number of turns to wait if the player got a punishment.
	char *name; //Holds the name of the player.
};

struct block
{
	int block_id; //Holds id of the block.
	char *name; //Holds text of the block that is shown on the top of the block.
	int price; //If the block is property, holds the price of property. If the block is a tax
	//block than holds the tax fee and if the block is punishment block, then holds the number of turns to wait.
	int rent; //Holds the default rent of a property (no house).
	int rent_1;	//Holds the rent of a property with one house.
	int rent_2; //Holds the rent of a property with two houses.
	int rent_3; //Holds the rent of a property with three houses.
	int house_price; //Holds price of building a house on the block.
	int house_count; //Holds the number of the houses on the block that are already built.
	struct player owner; //Holds the owner of the block.
	block_type type; //Holds type of the block. The block_type must be defined as
	//enumerated type which consist of start, property, tax, punish values.
};

void init_the_board(struct block board[20]){
	for(int i = 0; i < 20 ; i++){
		board[i].block_id = i;
		board[i].owner.type = noone;
		board[i].house_count = 0;
		if( i == 0 ){
			board[i].name = "Start";
			board[i].price = 0;
			board[i].rent = 0;
			board[i].rent_1 = 0;
			board[i].rent_2 = 0;
			board[i].rent_3 = 0;
			board[i].house_price = 0;
			board[i].type = start;
		}
		else if( i == 1){
			board[i].name = "Esenyurt";
			board[i].price = 16000;
			board[i].rent = 800;
			board[i].rent_1 = 4000;
			board[i].rent_2 = 9000;
			board[i].rent_3 = 25000;
			board[i].house_price = 10000;
			board[i].type = property;
		}
		else if( i == 2){
			board[i].name = "Car Park";
			board[i].price = 0;
			board[i].rent = 1500;
			board[i].rent_1 = 0;
			board[i].rent_2 = 0;
			board[i].rent_3 = 0;
			board[i].house_price = 0;
			board[i].type = tax;
		}
		else if( i == 3){
			board[i].name = "Tuzla";
			board[i].price = 16500;
			board[i].rent = 850;
			board[i].rent_1 = 4250;
			board[i].rent_2 = 9500;
			board[i].rent_3 = 26000;
			board[i].house_price = 12000;
			board[i].type = property;
		}
		else if( i == 4){
			board[i].name = "Arnavutköy";
			board[i].price = 17000;
			board[i].rent = 875;
			board[i].rent_1 = 4500;
			board[i].rent_2 = 10000;
			board[i].rent_3 = 28000;
			board[i].house_price = 12000;
			board[i].type = property;
		}
		else if( i == 5){
			board[i].name = "Wait 2 Turn";
			board[i].price = 0;
			board[i].rent = 2;
			board[i].rent_1 = 0;
			board[i].rent_2 = 0;
			board[i].rent_3 = 0;
			board[i].house_price = 0;
			board[i].type = punish;
		}
		else if( i == 6){
			board[i].name = "Çatalca";
			board[i].price = 20000;
			board[i].rent = 1000;
			board[i].rent_1 = 5000;
			board[i].rent_2 = 12000;
			board[i].rent_3 = 30000;
			board[i].house_price = 13000;
			board[i].type = property;
		}
		else if( i == 7){
			board[i].name = "Beykoz";
			board[i].price = 23000;
			board[i].rent = 1100;
			board[i].rent_1 = 5500;
			board[i].rent_2 = 12500;
			board[i].rent_3 = 33000;
			board[i].house_price = 13000;
			board[i].type = property;
		}
		else if( i == 8){
			board[i].name = "Car Fix";
			board[i].price = 0;
			board[i].rent = 1750;
			board[i].rent_1 = 0;
			board[i].rent_2 = 0;
			board[i].rent_3 = 0;
			board[i].house_price = 0;
			board[i].type = tax;
		}
		else if( i == 9){
			board[i].name = "Maltepe";
			board[i].price = 30000;
			board[i].rent = 1350;
			board[i].rent_1 = 7000;
			board[i].rent_2 = 15000;
			board[i].rent_3 = 40000;
			board[i].house_price = 15000;
			board[i].type = property;
		}
		else if( i == 10){
			board[i].name = "Bills";
			board[i].price = 0;
			board[i].rent = 2000;
			board[i].rent_1 = 0;
			board[i].rent_2 = 0;
			board[i].rent_3 = 0;
			board[i].house_price = 0;
			board[i].type = tax;
		}
		else if( i == 11){
			board[i].name = "Şişli";
			board[i].price = 33000;
			board[i].rent = 1500;
			board[i].rent_1 = 8000;
			board[i].rent_2 = 16000;
			board[i].rent_3 = 42000;
			board[i].house_price = 16000;
			board[i].type = property;
		}
		else if( i == 12){
			board[i].name = "Oil";
			board[i].price = 0;
			board[i].rent = 2500;
			board[i].rent_1 = 0;
			board[i].rent_2 = 0;
			board[i].rent_3 = 0;
			board[i].house_price = 0;
			board[i].type = tax;
		}
		else if( i == 13){
			board[i].name = "Ataşehir";
			board[i].price = 35000;
			board[i].rent = 1600;
			board[i].rent_1 = 8500;
			board[i].rent_2 = 17000;
			board[i].rent_3 = 45000;
			board[i].house_price = 17000;
			board[i].type = property;
		}
		else if( i == 14){
			board[i].name = "Sarıyer";
			board[i].price = 40000;
			board[i].rent = 1750;
			board[i].rent_1 = 9500;
			board[i].rent_2 = 19000;
			board[i].rent_3 = 48000;
			board[i].house_price = 19000;
			board[i].type = property;
		}
		else if( i == 15){
			board[i].name = "Wait 1 Turn";
			board[i].price = 0;
			board[i].rent = 1;
			board[i].rent_1 = 0;
			board[i].rent_2 = 0;
			board[i].rent_3 = 0;
			board[i].house_price = 0;
			board[i].type = punish;
		}
		else if( i == 16){
			board[i].name = "Kadıköy";
			board[i].price = 43000;
			board[i].rent = 1900;
			board[i].rent_1 = 11000;
			board[i].rent_2 = 21500;
			board[i].rent_3 = 55000;
			board[i].house_price = 23000;
			board[i].type = property;
		}
		else if( i == 17){
			board[i].name = "Beşiktaş";
			board[i].price = 60000;
			board[i].rent = 2500;
			board[i].rent_1 = 15000;
			board[i].rent_2 = 28000;
			board[i].rent_3 = 60000;
			board[i].house_price = 30000;
			board[i].type = property;
		}
		else if( i == 18){
			board[i].name = "Vocation";
			board[i].price = 0;
			board[i].rent = 5000;
			board[i].rent_1 = 0;
			board[i].rent_2 = 0;
			board[i].rent_3 = 0;
			board[i].house_price = 0;
			board[i].type = tax;
		}
		else if( i == 19){
			board[i].name = "Bebek";
			board[i].price = 70000;
			board[i].rent = 3500;
			board[i].rent_1 = 20000;
			board[i].rent_2 = 35500;
			board[i].rent_3 = 65000;
			board[i].house_price = 35000;
			board[i].type = property;
		}
	}
}
void show_board(struct block board[20], struct player player_one, struct player player_two){
	int i,j,k, count = 0, countdown = 19, a = count, b = countdown;
	for( i = 0; i < 6 ; i++){
		printf("------------------------");
	}
	printf("\n\n");
	for( i = 0 ; i < 6 ; i++){
		if( i == 0 ){

			for( j = 0 ; j < 6 ; j++){
				if( strlen( board[count].name ) <= 5){
					printf("|\t%s\t\t", board[count].name);
				}
				else
					printf("|\t%s\t", board[count].name);
				count++;
			}
			printf("|\n\n");
			count = a;
			for( j = 0 ; j < 6 ; j++){
				if( board[count].type == property){
					printf("|\t%d$\t\t", board[count].price);
				}
				else if( board[count].type == tax ){
					printf("|\t%d$\t\t", board[count].rent);
				}
				else
					printf("|\t\t\t");
				count++;
			}
			printf("|\n\n");
			count = a;
			for( j = 0 ; j < 6 ; j++){
				if( player_one.current_block_id == count && player_two.current_block_id == count){
					printf("|\t%s\t%s\t", player_one.name, player_two.name);
				}
				else if(player_one.current_block_id == count){
					printf("|\t%s\t\t", player_one.name);
				}
				else if(player_two.current_block_id == count){
					printf("|\t%s\t\t", player_two.name);
				}
				else
					printf("|\t\t\t");
				count++;	
			}
			printf("|\n\n");
			a = count;
			for( j = 0; j < 6 ; j++){
				printf("------------------------");
			} 
			printf("\n\n");
		}
		else if( i == 5){
			for( j = 0 ; j < 6 ; j++){
				if( strlen( board[countdown].name) <= 5){
					printf("|\t%s\t\t", board[countdown].name);
				}
				else
					printf("|\t%s\t", board[countdown].name);
				if( countdown == 14 || countdown == 11){
					printf("\t");
				}
				countdown--;
			}
			printf("|\n\n");
			countdown = b;
			for( j = 0 ; j < 6 ; j++){
				if( board[countdown].type == property){
					printf("|\t%d$\t\t", board[countdown].price);
				}
				else if( board[countdown].type == tax ){
					printf("|\t%d$\t\t", board[countdown].rent);
				}
				else
					printf("|\t\t\t");	
				countdown--;
			}
			printf("|\n\n");
			countdown = b;
			for( j = 0 ; j < 6 ; j++){
				if( player_one.current_block_id == countdown && player_two.current_block_id == countdown){
					printf("|\t%s\t%s\t", player_one.name, player_two.name);
				}
				else if(player_one.current_block_id == countdown){
					printf("|\t%s\t\t", player_one.name);
				}
				else if(player_two.current_block_id == countdown){
					printf("|\t%s\t\t", player_two.name);
				}
				else
					printf("|\t\t\t");
				countdown--;	
			}
			printf("|\n\n");
			b = countdown;
			for( j = 0; j < 6 ; j++){
				printf("------------------------");
			} 
			printf("\n\n");
		}
		else{
			for( j = 0 ; j < 6 ; j++){
				if( j == 5){
					if( strlen( board[count].name) <= 5){
						printf("|\t%s\t\t\t|", board[count].name);
					}
					else
						printf("|\t%s\t\t|", board[count].name);
					count++;
				}
				else if( j == 0){
					if( strlen( board[countdown].name) <= 5){
						printf("|\t%s\t\t", board[countdown].name);
					}
					else
						printf("|\t%s\t", board[countdown].name);
					if(countdown == 16){
						printf("\t|");
					}
					else
						printf("|");
					countdown--;
				}
				else
					printf("\t\t\t");
			}
			printf("\n\n");
			count = a;
			countdown = b;
			for( j = 0 ; j < 6 ; j++){
				if( j == 5){
					if( board[count].type == property){
						printf("|\t%d$\t\t|", board[count].price);
					}
					else if( board[count].type == tax ){
						printf("|\t%d$\t\t|", board[count].rent);
					}
					else
						printf("|\t\t\t");
					count++;
				}
				else if( j == 0 ){
					if( board[countdown].type == property){
						printf("|\t%d$\t\t|", board[countdown].price);
					}
					else if( board[countdown].type == tax ){
						printf("|\t%d$\t\t|", board[countdown].rent);
					}	
					countdown--;
				}
				else
					printf("\t\t\t");
			}
			printf("\n\n");
			count = a;
			countdown = b;
			for( j = 0 ; j < 6 ; j++){
				if( j == 5){
					if( player_one.current_block_id == count && player_two.current_block_id == count){
						printf("|\t%s\t%s\t|", player_one.name, player_two.name);
					}
					else if(player_one.current_block_id == count){
						printf("|\t%s\t\t|", player_one.name);
					}
					else if(player_two.current_block_id == count){
						printf("|\t%s\t\t|", player_two.name);
					}
					else
						printf("|\t\t\t|");
					count++;
				}
				else if( j == 0 ){
					if( player_one.current_block_id == countdown && player_two.current_block_id == countdown){
						printf("|\t%s\t%s\t|", player_one.name, player_two.name);
					}
					else if(player_one.current_block_id == countdown){
						printf("|\t%s\t\t|", player_one.name);
					}
					else if(player_two.current_block_id == countdown){
						printf("|\t%s\t\t|", player_two.name);
					}
					else
						printf("|\t\t\t|");
					countdown--;
				}
				else
					printf("\t\t\t");
			}
			printf("\n\n");
			a = count;
			b = countdown;

			for( j = 0; j < 6 ; j++){
				if(  j == 0 || j == 5 || i == 4 ){
					printf("------------------------");
				}
				else
					printf("\t\t\t");
			}
			printf("\n\n");
			
		}
	}
}

void show_properties(struct block board[20]){
	int i,a;
	printf("Please select a property to see details:\n");
	for( i = 0 ; i < 20 ; i++){
		if(board[i].type == property){
			printf("%d-\t", i+1);
			printf("%s\n", board[i].name);
		}
	}
	printf("0-\tExit\n");
	scanf("%d", &a);
	a = a - 1;
	if( board[a].type == property){
		for( i = 0 ; i < 8 ; i++){
			printf("-----");
		}
		printf("\n|\t\t%s\t\t", board[a].name);
		if( strlen( board[a].name ) <= 5){
			printf("\t|\n");
		}
		else
			printf("|\n");
		for( i = 0 ; i < 8 ; i++){
			printf("-----");
		}
		printf("\n|\tRent\t\t\t%d$\t|\n", board[a].rent);
		printf("|\tRent 1 H\t\t%d$\t|\n", board[a].rent_1);
		printf("|\tRent 2 H\t\t%d$\t|\n", board[a].rent_2);
		printf("|\tRent 3 H\t\t%d$\t|\n", board[a].rent_3);
		for( i = 0 ; i < 8 ; i++){
			printf("-----");
		}
		printf("\n|\tHouse Price\t\t%d$\t|\n", board[a].price);
		for( i = 0 ; i < 8 ; i++){
			printf("-----");
		}
		printf("\n");
	}	
}

void buy_property(struct block* current_block, struct player* current_player){
	if( current_block->type == property){
		if( current_block->owner.type == noone){
			if( current_block->block_id == current_player->current_block_id){
				if( current_player->account >= current_block->price){
					current_player->account = current_player->account - current_block->price;
					current_block->owner.type = current_player->type;
					current_player->owned_block_ids[current_block->block_id] = 1;
					printf("The block is successfuly purchased.\n");
				}
				else
					printf("You don't have enough money to buy this property.\n");
			}
			else
				printf("You are not on the same block.\n");
		}
		else
			printf("This block is already has a owner.\n");
	}
	else
		printf("This block can not be bought.\n");
}

void sell_property(struct block board[20], struct player* current_player){
	int i, a = 0, count = 0;
	printf("Which property do you want to sell ?\n");
	while( a != -1){
		for( i = 0; i < 20 ; i++){
			if( current_player->owned_block_ids[i] == 1){
				printf("%d-\t%s\n", i+1, board[i].name);
				count++;
			}
		}
		if( count == 0 ){
			printf("You don't have any property to sell.\n");
			break;
		}
		printf("0 - Exit\n");
		scanf("%d", &a);
		a = a - 1;
		if( current_player->owned_block_ids[a] == 1){
			if( board[a].owner.type == current_player->type){
				current_player->account = current_player->account + (board[a].price / 2);
				current_player->owned_block_ids[a] == -1;
				board[a].owner.type = noone;
				count--;
				printf(" The block has been saled successfully.\n");
			}
			else
				printf("This block doesn't belong to you.\n");
		}
		else
			printf("You don't have this property.\n");
		if( count == 0 ){
			printf("You don't have any property to sell.\n");
			break;
		}
	}	
}

void choices(int a){
	if( a == 0){
		printf("1 - Roll the dice\n");
		printf("2 - Show my account\n");
		printf("3 - Show my properties\n");
		printf("4 - Show property deeds\n");
		printf("5 - Buy property\n");
		printf("6 - Buy house\n");
		printf("7 - Sell property\n");
		printf("Please select an option to contunue: \n");
	}
	else if( a == 1){
		printf("2 - Show my account\n");
		printf("3 - Show my properties\n");
		printf("4 - Show property deeds\n");
		printf("5 - Buy property\n");
		printf("6 - Buy house\n");
		printf("7 - Sell property\n");
		printf("Please select an option to contunue: \n");
	}
}

void bankrupt( struct player *current_player, int debt, struct block board[20]){
	int i,count = 0;
	for( i = 0; i < 12 ; i++){
		if(current_player->owned_block_ids[i] == 1){
			count += board[i].price / 2;
		}
	}
	if( (count + current_player->account) < debt){
		printf("You went bankrupt.\n");
		exit(1);
	}
}

void roll_the_dice(struct player *current_player, struct block board[20], struct player *other_player){
	int a,count;
	srand(time(NULL));
	if( current_player->turn_to_wait == 0){
		a = (rand() % 5) + 1; //It cannot be 0
		printf("Rolling the dice....\n");
		printf("Dice is %d\n", a);
		current_player->current_block_id = current_player->current_block_id + a;
		if( current_player->current_block_id >= 20 ){
			current_player->current_block_id = current_player->current_block_id % 20;
			current_player->account = current_player->account + 10000;
			printf("You have finished a turn and gained 10000$\n");
		}
		if( board[current_player->current_block_id].type == tax){
			if( current_player->account >= board[current_player->current_block_id].rent){
				current_player->account = current_player->account - board[current_player->current_block_id].rent;
			}
			else{
				bankrupt(current_player, board[current_player->current_block_id].rent,board);
				sell_property(board, current_player);
				if( current_player->account >= board[current_player->current_block_id].rent){
					current_player->account = current_player->account - board[current_player->current_block_id].rent;
				}
			}
		}
		else if( board[current_player->current_block_id].type == punish){
			current_player->turn_to_wait = board[current_player->current_block_id].rent;
			printf("You need to wait %d turns to get rid of the penalty\n", current_player->turn_to_wait);
		}
		else if( board[current_player->current_block_id].owner.type == other_player->type){
			if(board[current_player->current_block_id].house_count == 0){
				bankrupt(current_player, board[current_player->current_block_id].rent,board);
				if( current_player->account < board[current_player->current_block_id].rent){
					sell_property(board,current_player);
				}
				current_player->account = current_player->account - board[current_player->current_block_id].rent;
				other_player->account = other_player->account + board[current_player->current_block_id].rent;
			}
			else if(board[current_player->current_block_id].house_count == 1){
				bankrupt(current_player, board[current_player->current_block_id].rent_1,board);
				if( current_player->account < board[current_player->current_block_id].rent_1){
					sell_property(board,current_player);
				}
				current_player->account = current_player->account - board[current_player->current_block_id].rent_1;
				other_player->account = other_player->account + board[current_player->current_block_id].rent_1;
			}
			else if(board[current_player->current_block_id].house_count == 2){
				bankrupt(current_player, board[current_player->current_block_id].rent_2,board);
				if( current_player->account < board[current_player->current_block_id].rent_2){
					sell_property(board,current_player);
				}
				current_player->account = current_player->account - board[current_player->current_block_id].rent_2;
				other_player->account = other_player->account + board[current_player->current_block_id].rent_2;
			}
			else if(board[current_player->current_block_id].house_count == 3){
				bankrupt(current_player, board[current_player->current_block_id].rent_3,board);
				if( current_player->account < board[current_player->current_block_id].rent_3){
					sell_property(board,current_player);
				}
				current_player->account = current_player->account - board[current_player->current_block_id].rent_3;
				other_player->account = other_player->account + board[current_player->current_block_id].rent_3;
			}
		}
	}
	else{
		printf("You need to wait %d more rounds to get rid of the penalty\n", current_player->turn_to_wait);
		current_player->turn_to_wait--;
	}	
}
void my_properties(struct player current, struct block board[20]){
	int i,j,count = 0;
	for(i = 0 ; i < 12 ; i++){
		if( current.owned_block_ids[i] == 1){
			for(j = 0 ; j < 20 ; j++){
				if(board[j].type == property){
					count++;
				}
				if( count == i){
					printf("%d - %s\n", i+1, board[j].name);
					count = 0;
					break;
				}
			}
		}
	}
}

void show_deeds(struct player current, struct block board[20]){
	int a,i;
	my_properties(current,board);
	printf("Which one ?\n");
	scanf("%d", &a);
	a = a - 1;
	if( board[a].type == property){
		for( i = 0 ; i < 8 ; i++){
			printf("-----");
		}
		printf("\n|\t\t%s\t\t", board[a].name);
		if( strlen( board[a].name ) <= 5){
			printf("\t|\n");
		}
		else
			printf("|\n");
		for( i = 0 ; i < 8 ; i++){
			printf("-----");
		}
		printf("\n|\tRent\t\t\t%d$\t|\n", board[a].rent);
		printf("|\tRent 1 H\t\t%d$\t|\n", board[a].rent_1);
		printf("|\tRent 2 H\t\t%d$\t|\n", board[a].rent_2);
		printf("|\tRent 3 H\t\t%d$\t|\n", board[a].rent_3);
		for( i = 0 ; i < 8 ; i++){
			printf("-----");
		}
		printf("\n|\tHouse Price\t\t%d$\t|\n", board[a].price);
		for( i = 0 ; i < 8 ; i++){
			printf("-----");
		}
		printf("\n");
	}	
}
void buy_house(struct block* current_block, struct player *current_player){
	if( current_block->house_count != 3){
		if( current_player->account < current_block->house_price){
			printf("You don't have enough money to buy house\n");
		}
		else{
			current_player->account = current_player->account - current_block->house_price;
			current_block->house_count++;
			printf("The house has been sold.\n");
		}
	}
	else
		printf("No more houses can be purchased in this block\n");

}

void gameplay (struct block board[20], struct player player_one, struct player player_two){
	int count = 0, choice; 
	while( 1 ){
		show_board(board, player_one, player_two);
		if( count % 2 == 0){
			printf("Player 1's turn.\n");
			while( choice != 1){	
				choices(0);
				scanf("%d", &choice);
				switch(choice){
					case 1:	roll_the_dice(&player_one, board, &player_two); break;
					case 2:	printf("Player 1's Account: %d\n", player_one.account); break;
					case 3:	my_properties(player_one,board); break;
					case 4:	show_deeds(player_one,board); break;
					case 5:	buy_property( &board[player_one.current_block_id], &player_one); break;
					case 6:	buy_house( &board[player_one.current_block_id], &player_one); break; 
					case 7:	sell_property(board, &player_one); break;
					default:	break;
				}
			}
			choices(1);
			scanf("%d", &choice);
			switch(choice){
				case 2:	printf("Player 1's Account: %d\n", player_one.account); break;
				case 3:	my_properties(player_one,board); break;
				case 4:	show_deeds(player_one,board); break;
				case 5:	buy_property( &board[player_one.current_block_id], &player_one); break;
				case 6:	buy_house( &board[player_one.current_block_id], &player_one); break; 
				case 7:	sell_property(board, &player_one); break;
				default:	break;
			}
		}
		else if( count % 2 == 1){
			printf("Player 2's turn.\n");
			while( choice != 1){		
				choices(0);
				scanf("%d", &choice);
				switch(choice){
					case 1:	roll_the_dice(&player_two, board, &player_one); break;
					case 2:	printf("Player 2's Account: %d\n", player_two.account); break;
					case 3:	my_properties(player_two,board); break;
					case 4:	show_deeds(player_two,board); break;
					case 5:	buy_property( &board[player_one.current_block_id], &player_one); break;
					case 6:	buy_house( &board[player_two.current_block_id], &player_two); break;
					case 7:	sell_property(board, &player_two); break;
					default:	break;
				}
			}
			choices(1);
			scanf("%d", &choice);
			switch(choice){
				case 2:	printf("Player 2's Account: %d\n", player_two.account); break;
				case 3:	my_properties(player_two,board); break;
				case 4:	show_deeds(player_two,board); break;
				case 5:	buy_property( &board[player_two.current_block_id], &player_two); break;
				case 6:	buy_house( &board[player_two.current_block_id], &player_two); break; 
				case 7:	sell_property(board, &player_two); break;
				default:	break;
			}	
		}
		count++;
		choice = 0;
	}

}

int main(){

	struct block board[20];
	struct player player_one, player_two;

	player_one.name = "Cap";
	player_two.name = "Car";
	player_one.type = cap;
	player_two.type = car;
	player_one.current_block_id = 0;
	player_two.current_block_id = 0;
	player_one.account = 100000;
	player_two.account = 100000;
	player_one.turn_to_wait = 0;
	player_two.turn_to_wait = 0;

	init_the_board(board);
	gameplay(board,player_one,player_two);

}

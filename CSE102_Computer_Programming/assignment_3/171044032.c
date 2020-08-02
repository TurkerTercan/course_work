			/********************************************************************************************************************************************/
			//		TURKER TERCAN		171044032			CSE 102		      HW2	`			            //
			/********************************************************************************************************************************************/

#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void lucky_number(int* score_human, int* score_program);
int make_a_guess(int trial, int min, int max);
void show_scores(int* score_human, int* score_program);
void draw_hourglass (int height);
void draw_mountain_road (int length, int max_radius);
void draw_left_road(int radius, int max_radius);
void draw_right_road(int radius, int max_radius);
void menu();

int main(){
	menu();
	return 0;	
}

void menu(){
	int i = 0, height = 0;
	int length, radius;
	int score_human = 0, score_program = 0;
	while( i != 4 ){ 
		printf("***** MENU *****\n");
		printf("1. Play Lucky Number\n");
		printf("2. Draw Hourglass\n");
		printf("3. Draw Mountain Road\n");
		printf("4. Exit\n");
		printf("Choice: ");
		scanf("%d", &i);
		switch( i ){
			case 1:		lucky_number(&score_human,&score_program); break;
			case 2:		draw_hourglass(height); break;
			case 3:		draw_mountain_road(length, radius); break;
			case 4:		exit(1); break;											// After many choices in the menu, i want to exit without exit function and i entered 4 but
			default:	printf("This is an invalid choice. Try again!\n");		// it didn't close. After 4 or 5 five enters it closed. I couldn't figure it out why is this
		}																		// happening. So i used exit function.
	}
	
}
void lucky_number(int* score_human, int* score_program){
	int ln = 1025, gn;								//I assigned to ln 1025 because it needs to enter the while loop//
	int min = 1, max = 1024;
	int trial, distance;
	srand(time(NULL));								
	while( ln < 1 || ln > 1024){
		ln = (rand() % 1024) + 1;
	}
	for( trial = 1 ; trial <= 10 ; trial++){
		gn = make_a_guess(trial, min, max);
		if( gn < ln && gn > min) 					//Checking for which limit is going to be taken by guess number//
			min = gn;
		else if( gn > ln && gn < max )
			max = gn;
		if( (gn - ln) < 0 ){						//Just making sure the extraction will always be positive//
			distance = ln - gn; 
		}
		else
			distance = gn - ln;
		if( distance >= 512 && distance <= 1023 )				//Checking for distances//
            printf("Distance: 10\n");
        else if( distance >= 256 && distance <= 511 )
            printf("Distance: 9\n");
        else if( distance >= 128 && distance <= 255 )
            printf("Distance: 8\n");
        else if( distance >= 64 && distance <= 127 )
            printf("Distance: 7\n");
        else if( distance >= 32 && distance <= 63 )
            printf("Distance: 6\n");
        else if( distance >= 16 && distance <= 31 )
            printf("Distance: 5\n");
        else if( distance >= 8 && distance <= 15 )
            printf("Distance: 4\n");
        else if( distance >= 4 && distance <= 7 )
            printf("Distance: 3\n");
        else if( distance >= 2 && distance <= 3 )
            printf("Distance: 2\n");
        else if( distance == 1 )
            printf("Distance: 1\n");
        else if( distance == 0 ){
            printf("Distance: 0 You win!\n");
            break;
        }
	}
	if( distance != 0){
		*score_program = *score_program + 1;
	}
	else if( distance == 0){							//Pointers helped me to return them. So after the function closes, there won't be any data loss.
		*score_human = *score_program + 1;
	}
	show_scores(score_human, score_program);
	printf("\n");
	
}

int make_a_guess (int trial, int min, int max){
	int number;
	printf("(Trial: %d) Make a guess between %d and %d: ", trial, min, max);
	scanf("%d", &number);
	while ( number < min || number > max){
		printf("Error: Please enter a number between given numbers.\n");		//If the number is not between the limits, function needs another number from the user.//
		scanf("%d", &number);
	}
	return number;
}

void show_scores(int* score_human, int* score_program){

	printf("Your Score: %d, Program Score: %d\n", *score_human, *score_program);
}


void draw_hourglass (int height){

	int i, j, space = 1;
	printf("Enter an odd number for Hourglass: ");
	scanf("%d", &height);
	while( height % 2 != 1 || height <= 0){
		printf("Error: Please enter an odd number. Try Again! \n");
		scanf("%d", &height);
	}
	height = height / 2 + 1;
	//Printing an reverse pyramid triangle
	for( i = height; i >= 1; i--){
		for( space = 0 ; space < height - i; space++)
			printf(" ");
		for(j = i; j <= 2* i - 1; j++)
			printf("*");
		for(j = 0; j< i - 1; j++)
			printf("*");
		printf("\n");
	}
	//Prints normal pyramid triange without a star in the top.
	for( i = 2; i <= height; i++){
		space = 0;
		for( j = 1; j <= height - i; j++){
			printf(" ");
		}
		for( space = 0; space != 2*i -1; space++){
			printf("*");
		}

		printf("\n");
	}
	printf("\n");
}

void draw_mountain_road (int length, int max_radius){
	int i,radius,j;
	srand(time(NULL));
	printf("Enter your half circles for mountain road: ");
	scanf("%d", &length);
	printf("Enter your maximum radius value: ");
	scanf("%d", &max_radius);
	

	for( i = 1; i <= length ; i++){
		radius = (rand() % max_radius);  			// Radius would be between 0 and max radius.
		if( radius == 0 ){
			for( j = 1 ; j <= max_radius; j++){
				printf(" ");
			}
			if( i % 2 == 1){
				printf("\b"); 						//I put here a backspace because if the road is coming from right and the next radius is zero
			}										//it causes to not printing properly. So i used a backspace to fix it.
			printf("|\n");
		}
		else if( i % 2 == 0)
			draw_right_road(radius, max_radius);	//I could done it with just one function but i thought it would be more organized with this way. 
		else if( i % 2 == 1 ){
			draw_left_road(radius, max_radius);
		}
	}
	printf("\n");
}
//There won't be any overflow because spaces are printing according to maximum radius.

void draw_left_road(int radius, int max_radius){
	int i = 0,j = 0 ,k =0;
	//Printing upside of the rode
	for( i = 1; i <= radius; i++){
		for( j = 1; j <= max_radius - i; j++){
			printf(" ");
		}
		printf("/\n");
		
	}
	//Printing middle side of the rode
	for( k = 2; k < j ; k++){
		printf(" ");
	}
	printf("|\n");
	//Printing downside of the rode
	for( ; i > 1 ; i--){
		for( j = 0; j <= max_radius - i; j++){
			printf(" ");
		}
		printf("\\ \n");
	}
}

void draw_right_road(int radius, int max_radius){
	int i = 0, j = 0, k = 0;
	//Printing upside of the rode
	for( i = 1; i <= radius; i++){
		for( j = 1; j < max_radius + i; j++){
			printf(" ");
		}
		printf("\\ \n");
		
	}
	//Printing middle side of the rode
	for( k = 0; k < j ; k++){
		printf(" ");
	}
	printf("|\n");
	//Printing downside of the rode
	for( ; i > 1 ; i--){
		for( j = 2; j < max_radius + i; j++){
			printf(" ");
		}

		printf("/\n");
	}
}

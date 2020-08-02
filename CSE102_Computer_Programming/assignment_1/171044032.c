/* ================ 171044032 ================ Turker Tercan ================ CSE 102 HW-1 ================ */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

#define PI 3
#define SQUARE 1
#define RECTANGULAR 2
#define CIRCULAR 3
#define Red 0
#define Yellow 1
#define Blue 2
#define Black 3
#define White 4

double CreateBody (int shape){
	double size;
	double short_edge, long_edge, radius, edge;

	if( shape == SQUARE ){
		//Area of an square
		printf("How long is the edge of the pokemon: ");
		scanf("%lf", &edge);
		printf("\n");
		size = edge * edge;
		return size;
	}
	else if( shape == RECTANGULAR ){
		//Area of an rectangular.
		printf("How long is the short edge of the pokemon: ");
		scanf("%lf", &short_edge);
		printf("\n");
		printf("How long is the long edge of the pokemon: ");
		scanf("%lf", &long_edge);
		printf("\n");
		size = short_edge * long_edge;
		return size;
	}
	else if( shape == CIRCULAR ){
		//Area of an circle
		printf("How long is the radius of the pokemon: ");
		scanf("%lf", &radius);
		printf("\n");
		size = PI * radius * radius;
		return size;
	}
}

int SetColor (int color){
	// Red: 0 // Yellow: 1 // Blue: 2 // Black: 3 // White: 4 //
	if( color <= 1000 || color >= 1 ){
		color = color % 5;
		return color;
	}
	//If the color integer is not between 1 and 1000, it returns to 1.
	else{
		return 1;
	}
}

double LoadMoves(int shape, double body_size){
	double radius, perimeter, edge;
	if( shape == SQUARE ){
		//Calculates a edge of the square and than finds perimeter.
		edge = sqrt(body_size);
		perimeter = 4 * edge;
		return perimeter;
	}
	else if( shape == RECTANGULAR ){
		//Assuming that shorter edge is 5.
		//First calculating the edge of the rectangular that is given its area
		//And calculates perimeter of a rectangular.
		edge = body_size / 5;
		perimeter = (edge * 2) + (2 * 5);
		return perimeter;
	}
	else if( shape == CIRCULAR ){
		//First calculating the radius of the circle that is given its area
		//And calculates perimeter of a circle.
		radius = body_size / PI;
		radius = sqrt(radius);
		perimeter = 2 * PI * radius;
		return perimeter;
	}
}

int SetAttackPower(int lower_bound, int upper_bound){
	int power;
	while( 1 ){
		//while condition checks if power is within specified range
		srand(time(NULL));
		power = ( rand() % upper_bound ) + lower_bound;
		if ( power >= lower_bound && power <= upper_bound ){
			break;
		}
	}	 
	return power;
}

void ShowPokemon(int shape, double body_size, int color, double move_length, int attack_power){
	double edge, long_edge, radius;
	int i, j, x = 0;

	if( shape == SQUARE ){
		//Printing Square
		edge = sqrt(body_size);
		for( i = 0 ; i < edge ; i++ ){
			for ( j = 0 ; j < edge ; j++){
				printf("X");
			}
			printf("\n");
		}
		printf("Name: Square Pokemon\n");
	}

	else if( shape == RECTANGULAR ){
		//Printing Rectangular
		long_edge = body_size / 5; 
		for( i = 0 ; i < 5 ; i++ ){
			for( j = 0 ; j < long_edge ; j++){
				printf("X");
			}
			printf("\n");
		}
		printf("Name: Rectangular Pokemon\n");
	}

	else if( shape == CIRCULAR ){
		radius = sqrt( body_size / PI );
		//Printing Circle
		//If the radius is an odd number it prints an extra tab for midlle Xs
		if( (int)radius % 2 == 1 ){
			radius = radius / 2 + 1;
			//Upside of the circle
			for ( i = 1; i <= radius; i++){
				for( j = 1; j <= radius - i; j++){
					printf("\t");
				}
				while( x != (2*i - 1)){
					if( x == 0 || x == 2*i - 2){
						printf("X");
					}
					if ( i != 1 || i != radius ){
						printf("\t");
					}
					else
						printf("\t");
					x++;
				}
				x = 0;
				printf("\n");
			}
			radius--;
			//Underside of the circle
			for( i = radius; i>= 1; i--){
				for( j = 0; j <= radius - i; j++){
					printf("\t");
				}
				x = 0;
				while( x != (2*i - 1)){
					if( x == 0 || x == 2*i -2)
						printf("X");
					if ( i != 1 || i != radius ){
						printf("\t");
					}
					else
						printf("\t");
					x++;
				}
				printf("\n");
			}
		}
		//If radius is an even integer, it backspaces 4 characters and than prints X for the bottom and top.
		else if( (int)radius % 2 == 0 ){
			radius = radius / 2 + 1;
			//Upside
			for ( i = 1; i <= radius; i++){
				for( j = 1; j <= radius - i; j++){
					printf("\t");
				}
				while( x != (2*i - 1)){
					if ( i == 1 || i == radius + 1){
						printf("\b\b\b\bX");
					}
					else if( x == 0 || x == 2*i - 2){
						printf("X");
					}
					else
						printf("\t");
					x++;
				}
				x = 0;
				printf("\n");
			}
			radius--;
			//Underside
			for( i = radius; i>= 1; i--){
				for( j = 0; j <= radius - i; j++){
					printf("\t");
				}
				x = 0;
				while( x != (2*i - 1)){
					if ( i == 1 || i == radius + 1){
						printf("\b\b\b\bX");
					}
					else if( x == 0 || x == 2*i -2)
						printf("X");
					else
						printf("\t");
					x++;
				}
				printf("\n");
			}
		}
		printf("Name: Circular Pokemon\n");
	}
	printf("Size: %4.2f\n", body_size);
	printf("Color: ");
	switch( color ){
		case Red: 		printf("Red\n"); break;
		case Yellow:	printf("Yellow\n"); break;
		case Blue:		printf("Blue\n"); break;
		case Black:		printf("Black\n"); break;
		case White: 	printf("White\n"); break;
	}
	printf("Move: %4.2f\n", move_length);
	printf("Attack Power: %d\n", attack_power);
}

int main(){

	int shape, color, attack_power;
	double size_of_body, move_length;
	shape = CIRCULAR; //RECTANGULAR / CIRCULAR / SQUARE // Pick one of them
	size_of_body = CreateBody (shape);
	color = 798;
	color = SetColor(color);
	move_length = LoadMoves(shape, size_of_body);
	attack_power = SetAttackPower (0, 150);
	ShowPokemon(shape, size_of_body, color, move_length, attack_power);

}

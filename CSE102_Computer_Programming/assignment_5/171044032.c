  /////////////////////////////////////////////////////////////////////////////////////////////
 //			TURKER  TERCAN 			171044032			CSE 102				HW5				//
/////////////////////////////////////////////////////////////////////////////////////////////

#include<stdio.h>
#include<stdlib.h>

#define DICT_SIZE 15
#define WORD_LEN 10
#define LINE_LEN 18

int compare( char name[10], char *cmp, int length );

int get_line_size(char *line) {
	char *ch_iter = line; // so as not to lose beginning of line
	int counter = 0;
	// go until you see new line or null char
	while(*ch_iter != '\n' && *ch_iter != '\0') {
		ch_iter++; // next char
		counter++; // increment counter
	}
	
	return counter;
}

void copy_string(char *source, char *destination) {
	// get iterators over original pointers
	char *src_iter = source;
	char *dst_iter = destination;
	// until null char
	while (*src_iter != '\0') {
		// copy pointers
		*dst_iter = *src_iter;
		// advance to next char
		src_iter++;
		dst_iter++;
   }
   // terminate string
   *dst_iter = '\0';
}

void remove_newline(char *line) {
	char *ch_iter = line;
	// go until you see new line
	while(*ch_iter != '\n') {
		ch_iter++; // next char
	}
	*ch_iter = '\0'; // overwrite new line
}

void print_dictionary(char *dict[]) {
	int i;
	for(i = 0 ; i < DICT_SIZE ; i++) {
		printf("%s\n", dict[i]);
	}
}

void print_coord(int coord[DICT_SIZE][4]) {
	int i, j;
	for(i = 0 ; i < DICT_SIZE ; i++) {
		for(j = 0 ; j < 4 ; j++) {
			printf("%d ", coord[i][j]);
		}
		printf("\n");
	}
}

char random_char(){
	int a;
	a = rand() % 26; //Numbers of letters in english.
	a += 97; //97 means 'a' in ASCII
	return a;
}

void place_words(char *dict[], int coord[DICT_SIZE][4], char area[15][15]){
	int i;
	int x,y;
	int t;
	for( i = 0; i < DICT_SIZE ; i++){
		t = 0;
		//Put letters into the area for y-axis
		if( coord[i][0] == coord[i][2] ){
			x = coord[i][0];
			y = coord[i][1];
			if( coord[i][1] < coord[i][3]){
				while( y <= coord[i][3]){	//If it is a vertical word, it means only the word's y coordinate will change and there won't be any difference with x. 
					area[y][x] = dict[i][t];
					t++;
					y++;
				}
			}
			else if( coord[i][3] < coord[i][1]){
				while( y >= coord[i][3]){	
					area[y][x] = dict[i][t];
					t++;
					y--;
				}
			}	
		}
		//Put letters into the area for x-axis
		else if( coord[i][1] == coord[i][3]){	//It is a horizantal word so only the x will increase.
			x = coord[i][0];
			y = coord[i][1];
			if( coord[i][0] < coord[i][2]){
				while( x <= coord[i][2]){
					area[y][x] = dict[i][t];
					t++;
					x++;
				}
			}
			else if( coord[i][2] < coord[i][0]){
				while( x >= coord[i][2]){
					area[y][x] = dict[i][t];
					t++;
					x--;
				}
			}	
		}

		//Put letters into the area for diagonal
		else if( coord[i][0] < coord[i][2]){
			x = coord[i][0];
			y = coord[i][1];
			if(coord[i][1] < coord[i][3]){
				while( x <= coord[i][2] ||  y <= coord[i][3] ){	//If it is diagonal x and also y will increase
					area[y][x] = dict[i][t];
					t++;
					x++;
					y++;
				}
			}
			else if(coord[i][3] < coord[i][1]){
				while( x <= coord[i][2] ||  y >= coord[i][3] ){
					area[y][x] = dict[i][t];
					t++;
					x++;
					y--;
				}

			}	
		}
		//Some of this codes won't be needed but I wanted to write them in that case, you can change the word.dat file
		else if( coord[i][2] < coord[i][0] ){
			x = coord[i][0];
			y = coord[i][1];
			if( coord[i][3] < coord[i][1]){
				while( x >= coord[i][2] ||  y >= coord[i][3] ){	//If it is diagonal x and also y will increase
					area[y][x] = dict[i][t];
					t++;
					x--;
					y--;
				}
			}
			else if( coord[i][1] < coord[i][3]){
				while( x >= coord[i][2] ||  y <= coord[i][3] ){	//If it is diagonal x and also y will increase
					area[y][x] = dict[i][t];
					t++;
					x--;
					y++;
				}
			}	
		}
	}
}

void print_patern( char area[15][15]){
	for(int i = 0; i < 15; i++){
		for(int j = 0; j < 15; j++){
			if(area[j][i] <= 'Z' && area[j][i] >= 'A'){	//If it is uppercase, it will be printed with red color. I used it because seesometimes i couldn't see uppercase letters.
				printf("\033[0;31m");
				printf("%c ", area[j][i]);
				printf("\033[0m");
			}
			else
				printf("%c ", area[j][i]);
		}
		printf("\n");
	}
}
void uppercase_letter(int coord[DICT_SIZE][4], char area[15][15], int i ){
	int a, b, t;
	//X-AXIS
	if( coord[i][0] == coord[i][2]){
		if( coord[i][1] < coord[i][3] ){
			a = coord[i][1];
			b = coord[i][3];
		}
		else{
			a = coord[i][3];
			b = coord[i][1];
		}
		while( a != b + 1){
			if( area[a][coord[i][0]] <= 'z' && area[a][coord[i][0]] >= 'a'){
				area[a][coord[i][0]] = area[a][coord[i][0]] - 32;
			}
			a++;
		}
	}
	//Y-AXIS
	else if( coord[i][1] == coord[i][3]){
		if( coord[i][0] < coord[i][2] ){
			a = coord[i][0];
			b = coord[i][2];
		}
		else{
			a = coord[i][2];
			b = coord[i][0];
		}

		while( a != b + 1){
			if(area[coord[i][1]][a] <= 'z' && area[coord[i][1]][a] >= 'a'){
				area[coord[i][1]][a] = area[coord[i][1]][a] - 32;
			}
			a++;
		}
	}
	//Diagonals
	else if( coord[i][0] < coord[i][2] && coord[i][1] < coord[i][3]){
		a = coord[i][0];
		b = coord[i][1];
		t = coord[i][2];
		while( a != t + 1){
			if( area[b][a] <= 'z' && area[b][a] >= 'a'){
				area[b][a] = area[b][a] - 32;
			}
			a++;
			b++;
		}
	}
	else if( coord[i][2] < coord[i][1] && coord[i][3] < coord[i][1]){
		a = coord[i][0];
		b = coord[i][1];
		t = coord[i][2];
		while( a != t - 1){
			if( area[b][a] <= 'z' && area[b][a] >= 'a'){
				area[b][a] = area[b][a] - 32;
			}
			a--;
			b--;
		}
	}
	else if( coord[i][0] < coord[i][2] && coord[i][3] < coord[i][1]){
		a = coord[i][0];
		b = coord[i][1];
		t = coord[i][2];
		while( a != t + 1){
			if( area[b][a] <= 'z' && area[b][a] >= 'a'){
				area[b][a] = area[b][a] - 32;
			}
			a++;
			b--;
		}
	}
	else if( coord[i][2] < coord[i][0] && coord[i][1] < coord[i][3]){
		a = coord[i][0];
		b = coord[i][1];
		t = coord[i][2];
		while( a != t - 1){
			if( area[b][a] <= 'z' && area[b][a] >= 'a'){
				area[b][a] = area[b][a] - 32;
			}
			a--;
			b++;
		}
	}
}

void play_game(char *dict[], int coord[DICT_SIZE][4], char area[15][15]){
	char status[15];
	int i,t = 0;
	int a = 0;
	int riddles = DICT_SIZE;
	int x1,x2,y1,y2,temp;
	x1 = -1;
	x2 = -1;
	y1 = -1;
	y2 = -1;
	place_words(dict, coord, area);
	print_patern(area);
	printf("Game is started.");
	
	while( riddles != 0 ){
		
		printf("Type the word that you've found.\nIf you want to close the game, type exit game.\n");
		printf("There are %d words need to be found\n", riddles);
		fgets(status, 15, stdin);
		if( compare( status, "exit game", get_line_size("exit game")) == 1 ){
			break;
		}
		printf("Type the word's starting or ending coordinates: \nY = ");
		while( y1 < 0 || y1 > 15){
			scanf("%d", &y1);
			if(y1 < 0 || y1 > 15){
				printf("Your coordinates were not right. Please type again.\n");
			}
		}
		printf("X = ");
		while( x1 < 0 || x1 > 15){
			scanf("%d", &x1);
			if(x1 < 0 || x1 > 15){
				printf("Your coordinates were not right. Please type again.\n");
			}
		}
		temp = x1;	//After i finished homework, i realized that i printed the area in reverse. 
		//To fix that i needed to change all xs and ys. Instead of doing that, i just changed x and y value to each other.
		//PS: area[y][x] should have been area[x][y]
		x1 = y1;
		y1 = temp;

		getchar(); //I need to put this getchar because after scanf, it ends with a newline character. It causes to block fgets function because it stops when a new line char is entered.
		//This checks if there is a word in dict like typed word and gets its other coordinates.

		for( i = 0 ; i < DICT_SIZE; i++){
			if( compare(status, dict[i], get_line_size(dict[i]) - 1) == 1){
				if( x1 == coord[i][0] && y1 == coord[i][1]){
					x2 = coord[i][2];
					y2 = coord[i][3];
				}
				else if( x1 == coord[i][2] && y1 == coord[i][3]){
					x2 = coord[i][0];
					y2 = coord[i][1];
				}
			
			}
		}
		


		//Y-AXIS
		if( x1 == x2 ){
			for( i = 0 ; i < DICT_SIZE ; i++){
				if( compare(status, dict[i], get_line_size(dict[i]) - 1 ) == 1){ //Finds the typed word.
					//Checks from top to bottom.
					if( y1 == coord[i][1] && x1 == coord[i][0] && x2 == coord[i][2] && y2 == coord[i][3]){	//First, checks that the coordinates are entered correctly and whether the coordinate is ending or beginning coordinate.
						t = 0;
						while( y1 != coord[i][3]){	//When it is equal to y2, it means whole word is checked.
							if(area[y1][x1] == dict[i][t]){	// Checks that the typed word is equal to the word in the area.
								t++;
								y1++;
							}
							else if( a < 1){ //I allowed to intersection of two words. If i hadn't done this, there would be an error in the words alveol or dolasim. 
								t++;
								a++;
								y1++;
							}
							else
								break; //If it is not equal, it breaks.
						}
						if( y1 == y2){
							uppercase_letter(coord,area,i); //Changes the typed word to uppercase in the area.
							riddles--;
						}
						else{
							printf("Your word or coordinates may not be correct or you've already found that word.\n");
						}

					}
					//Checks from bottom to top.
					else if( y2 == coord[i][1] && x2 == coord[i][0] && x1 == coord[i][2] && y1 == coord[i][3]){
						t = get_line_size(dict[i]) - 2;
						while( y1 != coord[i][1]){
							if(area[y1][x1] == dict[i][t]){
								t--;
								y1--;
							}
							else if( a < 1){ //I allowed to intersection of two words. If i hadn't done this, there would be an error in the words alveol or dolasim. 
								t--;
								a++;
								y1--;
							}
							else
								break;
						}
						if( y2 == y1 ){
							uppercase_letter(coord,area,i);
							riddles--;
						}
						else{
							printf("Your word or coordinates may not be correct or you've already found that word.\n");
						}

					}
					
				}
			}
		}
		//X-AXIS
		else if( y1 == y2){
			for( i = 0 ; i < DICT_SIZE ; i++){
				if( compare(status, dict[i], get_line_size(dict[i]) - 1) == 1){
					//Checks from left to right.
					if( y1 == coord[i][1] && x1 == coord[i][0] && x2 == coord[i][2] && y2 == coord[i][3]){
						t = 0;
						while( x1 != coord[i][2]){
							if(area[y1][x1] == dict[i][t]){
								t++;
								x1++;
							}
							else if( a < 1){ //I allowed 2 overlaps in a word. If i hadn't done this, there would be an error in the words alveol or dolasim. 
								t++;
								a++;
								x1++;
							}
							else
								break;	
						}
						if( x1 == x2){
							uppercase_letter(coord,area,i);
							riddles--;
						}
						else{
							printf("Your word or coordinates may not be correct or you've already found that word.\n");
						}


					}
					//Checks from right to left.
					else if( y2 == coord[i][1] && x2 == coord[i][0] && x1 == coord[i][2] && y1 == coord[i][3]){
						t = get_line_size(dict[i]) - 2; //In every dict ends with new line and null character.
						while( x1 != coord[i][0]){
							if(area[y1][x1] == dict[i][t]){
								t--;
								x1--;
							}
							else if( a < 1){ //I allowed to intersection of two words. If i hadn't done this, there would be an error in the words alveol or dolasim.  
								t--;
								a++;
								x1--;
							}
							else
								break;
						}
						if( x2 == x1 ){
							uppercase_letter(coord,area,i);
							riddles--;
						}
						else{
							printf("Your word or coordinates may not be correct or you've already found that word.\n");
						}

					}
				}
			}
		}
		//Diagonals
		for( i = 0; i < DICT_SIZE; i++){
			if( x1 < x2 && y1 < y2){
			//Checks from left top to right bottom.
				if( compare(status, dict[i], get_line_size(dict[i]) - 1 ) == 1){
					if( y1 == coord[i][1] && x1 == coord[i][0] && x2 == coord[i][2] && y2 == coord[i][3]){
						t = 0;
						while( x1 != coord[i][2] || y1 != coord[i][3]){
							if(area[y1][x1] == dict[i][t]){
								t++;
								x1++;
								y1++;
							}
							else if( a < 1){ //I allowed 2 overlaps in a word. If i hadn't done this, there would be an error in the words alveol or dolasim. 
								t++;
								a++;
								y1++;
								x1++;
							}
							else
								break;	
						}
						if( x1 == x2 && y1 == y2){
							uppercase_letter(coord,area,i);
							riddles--;
						}
						else{
							printf("Your word or coordinates may not be correct or you've already found that word.\n");
						}

					}
				}
			}		
			else if( x2 < x1 && y2 < y1){		
			//Checks from right bottom to left top.
				if( compare(status, dict[i], get_line_size(dict[i]) - 1) == 1){	
					if( y2 == coord[i][1] && x2 == coord[i][0] && x1 == coord[i][2] && y1 == coord[i][3]){
						t = get_line_size(dict[i]) - 2;
						while( y1 != coord[i][1] || x1 != coord[i][0]){
							if(area[y1][x1] == dict[i][t]){
								t--;
								y1--;
								x1--;
							}
							else if( a < 1){ //I allowed 2 overlaps in a word. If i hadn't done this, there would be an error in the words alveol or dolasim. 
								t--;
								a++;
								y1--;
								x1--;
							}
							else
								break;
						}
						if( y2 == y1  && x1 == x2){
							uppercase_letter(coord,area,i);
							printf("%s\n", dict[i]);
							riddles--;
						}
						else{
							printf("Your word or coordinates may not be correct or you've already found that word.\n");
						}

					}
				}
			}
			else if( x1 < x2 && y2 < y1){
			//Checks from left bottom to right top.
				if( compare(status, dict[i], get_line_size(dict[i]) - 1) == 1){
					if( y1 == coord[i][1] && x1 == coord[i][0] && x2 == coord[i][2] && y2 == coord[i][3] ){
						t = 0;
						while( x1 != coord[i][2] || y1 != coord[i][1]){
							if(area[y1][x1] == dict[i][t]){
								t++;
								x1++;
								y1--;
							}
							else if( a < 1){ //I allowed 2 overlaps in a word. If i hadn't done this, there would be an error in the words alveol or dolasim. 
								t++;
								a++;
								y1--;
								x1++;
							}
							else
								break;	
						}
						if( x1 == x2 && y1 == y2){
							uppercase_letter(coord,area,i);
							printf("%s\n", dict[i]);
							riddles--;
						}
						else{
							printf("Your word or coordinates may not be correct or you've already found that word.\n");
						}

					}	
				}
			}
			else if( x2 < x1 && y1 < y2){
			//Checks from right top to left bottom.
				if( compare(status, dict[i], get_line_size(dict[i]) - 1) == 1){
					if( y2 == coord[i][1] && x2 == coord[i][0] && x1 == coord[i][2] && y1 == coord[i][3] ){
						t = 0;
						while( x1 != coord[i][0] || y1 != coord[i][3]){
							if(area[y1][x1] == dict[i][t]){
								t--;
								x1--;
								y1++;
							}
							else if( a < 1){ //I allowed to intersection of two words. If i hadn't done this, there would be an error in the words alveol or dolasim. 
								t--;
								a++;
								y1++;
								x1--;
							}
							else
								break;	
						}
						if( x1 == x2 && y1 == y2){
							uppercase_letter(coord,area,i);
							printf("%s\n", dict[i]);
							riddles--;
						}
						else{
							printf("Your word or coordinates may not be correct or you've already found that word.\n");
						}

					}
				}
			}
		}
		print_patern(area);
		a = 0;
		x1 = -1;
		x2 = -1;
		y1 = -1;
		y2 = -1;
	}
	if( riddles == 0 ){
		printf("Congratulations! You have found all the words.\n");
	}
}

int compare( char name[10], char *cmp , int length){
	int i;
	for( i = 0; i < length; i++){
		if( name[i] != cmp[i]){
			return 0;
		}
		if( name[i] == '\0' && cmp[i] == '\0'){
			break;
		}
		
	}
	return 1;
}

int main(){

	char area[15][15];
	char *dict[DICT_SIZE];
    int coord[DICT_SIZE][4];    
    char line[LINE_LEN];
    
	FILE *fp = fopen("word_hunter.dat", "r");
	
	int line_counter = 0;
	int dict_counter = 0;
	while(fgets(line, LINE_LEN, fp) != NULL) {
		if(line_counter%5 == 0) {
			dict[dict_counter] = (char*) malloc(sizeof(char) * get_line_size(line));
			remove_newline(line);
			copy_string(line, dict[dict_counter]);
		} 
		else if (line_counter%5 == 1){
			coord[dict_counter][0] = atoi(line);
		} 
		else if (line_counter%5 == 2){			
			coord[dict_counter][1] = atoi(line);		
		} 
		else if (line_counter%5 == 3){
			coord[dict_counter][2] = atoi(line);
		} 
		else if (line_counter%5 == 4){
			coord[dict_counter][3] = atoi(line);
			dict_counter++;
		}
		line_counter++;
	}
	
	fclose(fp);
	//print_dictionary(dict);
	//print_coord(coord);
	
	// WRITE HERE...
	for(int i = 0; i < 15; i++){
		for(int j = 0; j < 15; j++){
			area[j][i] = random_char();
		}
	}
	play_game(dict, coord, area);
	return 0;
}
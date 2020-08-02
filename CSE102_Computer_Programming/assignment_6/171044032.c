  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 /////				TURKER TERCAN		171044032	CSE 102		HW6                        /////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

#include <stdio.h>

//PART 1
int common(int a, int b, int divisor){
	int greatest = 1;
	if( a < 0 ){
		a = a * -1;
	}
	if( b < 0 ){
		b = b * -1;
	}
	if( a % divisor == 0 && b % divisor == 0){
		greatest = common(a / divisor, b / divisor, divisor) * divisor;
	}
	else if( divisor < a && divisor < b){
		greatest = common( a, b, divisor + 1) * greatest;
	}
	else
		return greatest;
}
//PART 2
void increasing_order(int bottom, int mid, int top, int *list){
	int temp[200];
	int x,y,point;
	for(x = bottom, y = mid + 1, point = bottom; x <= mid && y <= top; point++){
		if( list[x] <= list[y]){	//It is checking both sides until at least one side is empty.
			temp[point] = list[x];
			x++;
		}
		else{
			temp[point] = list[y];
			y++;
		}
	}
	while( x <= mid ){	//If both sides are not empty, it assigns non-empty side to the temp
		temp[point] = list[x];	
		point++;
		x++;
	}
	while( y <= top){
		temp[point] = list[y];
		point++;
		y++;
	}
	for( x = bottom; x <= top; x++){	//Send values to the list.
		list[x] = temp[x];
	}
}


void divide_list(int a, int b, int *list){
	int c;
	if( a == b){
		return;
	}
	else{	
		c = (a + b)/2;	//Calculates midpoint
		divide_list( a, c , list);	//It calls recursively first half.
		divide_list( c + 1, b, list);	//Second half.
		increasing_order(a,c,b, list);	//Then, sort them every time.
	}
}


//PART 3
void calculate_formula( int n ){
	printf("%d ", n);
	if( n != 1){
		if( n % 2 == 0){	
			n = n / 2;
			calculate_formula(n);
		}
		else if( n % 2 == 1){
			n = (3 * n) + 1;
			calculate_formula(n);
		}
	}
	else
		return;
}

//PART 4
int power(int base, int expo){ //Recursive power function
	int ans = 1;
	if( expo == 0){
		return 1;
	}
	else
		ans = ans * power( base, expo - 1) * base;
	
	return ans;
}

int is_equal(int number, int n){
	int ans = 0;
	
	ans = power( number % 10, n) + ans;  //Every time it gets power of n and stores it in ans
	if( number < 10 ){	//If it is smaller than 10 function returns;
		return ans;
	}
	else
		ans = ans + is_equal(number/10, n);	
		return ans;
}

//PART 5
void uppercase(char *str){
	if( str[0] <= 'Z' && str[0] >= 'A'){
		printf("Output = %c\n", str[0]);
	}
	else if( str[1] == '\0' || str[1] == '\n' || str[1] == ' '){
		return;
	}
	else
		uppercase(str + 1);
}

int main(){
	int i, *n = 0;
	int array[200];
	char str[50];
	int a, b,x;
	int *list = &array[0];
	
	while( i != 0){	
		printf("PART 1: Find greatest common divisor\n");
		printf("PART 2: Increasing order.\n");
		printf("PART 3: Formula\n");
		printf("PART 4: Is it equal\n");
		printf("PART 5: First capital in string\n");
		printf("EXIT: 0\n");
		scanf("%d", &i);	
		switch(i){
			case 0:	return 0;
			case 1:	printf("Enter two numbers that you want to find their greatest common divisor\n");
			scanf("%d %d", &a, &b);
			printf("Greatest common divisor = %d\n", common(a,b,2));
			break;
			case 2: printf("Enter the length of the list: ");
			scanf("%d", &a);
			printf("Enter the elements of list:\n");
			for( int x = 0 ; x < a ; x++){
				scanf("%d", &list[x]);
			}
			divide_list(0, a, list);
			for( int x = 0 ; x < a ; x++){
				printf("%d ", list[x]);
			}
			printf("\n");
			break;
			case 3: printf("Input: ");
			scanf("%d", &a);
			printf("Output: ");
			calculate_formula(a);
			printf("\n");
			break;
			case 4: printf("Input= "); 
			scanf("%d", &a);
			b = 0;
			x = a;
			while( x != 0){
				x /= 10;
				b++;
			}
			if( a == is_equal(a,b)){
				printf("Output: Equal\n");
			}
			else
				printf("Output: Not Equal\n");
			break;
			case 5: printf("Input: ");
			scanf("%s", str);
			uppercase(str);
			break;
			default: printf("Wrong choice!");
		}
	}
}	

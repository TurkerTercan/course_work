//		TURKER	TERCAN		171044032		CSE 102		HW9		//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct person{
	char name[20];
	char surname[20] ;
	char musical_Work[20];
	int age;
	struct person *next;
} *top;


void addNode(char name[], char surname [], char creation [], int age){
	struct person *temp = (struct person*)malloc(sizeof(struct person));
	
	strcpy(temp->name, name);
	strcpy(temp->surname, surname);
	strcpy(temp->musical_Work, creation);
	temp->age = age;
	temp->next = top;
	top = temp;
}

void deleteNode(){
	struct person *temp;
	if( top == NULL ){
		printf("There is no person to delete from the stack\n");
	}
	else{
		temp = top;
		top = top->next;
		temp->next = NULL;
		free(temp);
	}
}

void swap( struct person *person_one, struct person *person_two){
	char name[20];
	char surname[20] ;
	char musical_Work[20];
	int age;
	strcpy(name, person_one->name);
	strcpy(surname, person_one->surname);
	strcpy(musical_Work, person_one->musical_Work);
	age = person_one->age;

	strcpy( person_one->name, person_two->name );
	strcpy( person_one->surname, person_two->surname );
	strcpy( person_one->musical_Work, person_two->musical_Work );
	person_one->age = person_two->age;

	strcpy( person_two->name, name);
	strcpy( person_two->surname, surname);
	strcpy( person_two->musical_Work, musical_Work);
	person_two->age = age;

}

void print_stack(){
	struct person *temp;
	temp = top;
	if( temp != NULL){
		printf("-----------------------------------------------------------------------------------------\n");
		printf("|\t   Name\t\t|\tSurname\t\t|\tMusical Work\t|\tAge\t|\n");
		while(temp != NULL){
			printf("-----------------------------------------------------------------------------------------\n");
			if(strlen(temp->name) > 7){
				printf("|\t%s\t|", temp->name);
			}
			else
				printf("|\t%s\t\t|", temp->name);
			if(strlen(temp->surname) > 7){
				printf("\t%s\t|", temp->surname);
			}
			else
				printf("\t%s\t\t|", temp->surname);
			if(strlen(temp->musical_Work) > 7){
				printf("\t%s\t|", temp->musical_Work);
			}
			else
				printf("\t%s\t\t|", temp->musical_Work);
			
			printf("\t%d\t|\n", temp->age);

			temp = temp->next;

		}
		printf("-----------------------------------------------------------------------------------------\n");
	}
	else{
		printf("There is no elements in the list.\n");
	}
}


void Sort_Alphabetically(){
	struct person *min;
	struct person *temp1;
	struct person *temp2;

	if( top == NULL){
		printf("There is no person to be sorted.\n");
	}
	else{
		for( temp1 = top; temp1 != NULL; temp1 = temp1->next){
			min = temp1;
			for( temp2 = temp1 ; temp2 != NULL; temp2 = temp2->next){
				if(strcmp( min->name , temp2->name) > 0){
					min = temp2;
				}
			}
			swap(min, temp1);
		}
		print_stack();
	}
	
}

void Sort_Increasingly(){
	struct person *min;
	struct person *temp1;
	struct person *temp2;
	if( top == NULL){
		printf("There is no person to be sorted.\n");
	}
	else{
		for( temp1 = top; temp1 != NULL; temp1 = temp1->next){
			min = temp1;
			for( temp2 = temp1 ; temp2 != NULL; temp2 = temp2->next){
				if( min->age > temp2->age){
					min = temp2;
				}
			}
			swap(min, temp1);

		}
		print_stack();
	}
}

void addPerson(){
	
	struct person someone;
	printf("Name: ");
	scanf("%[^\n]s ",someone.name);
	getchar();
	printf("Surname: ");
	scanf("%[^\n]s", someone.surname);
	getchar();
	printf("Musical Work: ");
	scanf("%[^\n]s ", someone.musical_Work);
	printf("Age: ");
	scanf("%d", &someone.age);
	addNode(someone.name, someone.surname, someone.musical_Work, someone.age);

}


int main()
{	
	int choice;
	
	while(1){
		
		printf("	****MENU****\n");
		printf(" 1- Add a Person to the Stack\n");
		printf(" 2- Pop a Person from the Stack\n");
		printf(" 3- Sort in Alphabetical Order\n");
		printf(" 4- Sort Age in Increasing Order\n");
		printf(" 5- Exit MENU\n");
		printf("	*************\n");

		printf("Enter your choice: \n");
		scanf("%d",&choice);
		switch(choice){
			case 1:	getchar(); addPerson(); break;
			case 2: deleteNode(); break;
			case 3: Sort_Alphabetically(); break;
			case 4: Sort_Increasingly(); break;
			case 5: return 0; break;
			default: printf("Wrong Choice! Try Again.\n"); break;
		}
	}	
	return 0;
}
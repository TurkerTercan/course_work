#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

typedef struct stack{
	int data;
	struct stack *nextptr;
}stack;

typedef struct queue{
	int data;
	struct queue *nextptr;
}queue;

typedef struct bst{
	int data;
	struct bst *left, *right;
}bst;

double fill_stack(stack ** stack_, int data[20]){
	int count = 0;
	clock_t starting_time = clock();
	stack *temp;

	(*stack_) = (stack*)malloc(sizeof(stack));
	(*stack_)->data = data[count++];
	(*stack_)->nextptr = NULL;
	

	while( count != 20){
		temp = (stack*)malloc(sizeof(stack));
		temp->data = data[count++];
		temp->nextptr = *stack_;
		*stack_ = temp;
	}

	clock_t ending_time = clock();

	return (double)(ending_time - starting_time) / CLOCKS_PER_SEC * 1000;
}

double fill_queue(queue **queue_, int data[20]){
	int count = 0;
	clock_t starting_time = clock();
	queue *temp;
	queue *node; 

	*queue_ = (queue*)malloc(sizeof(queue));
	(*queue_)->data = data[count++];
	(*queue_)->nextptr = NULL;
	
	temp = *queue_;
	while( count != 20 ){
		node = (queue*)malloc(sizeof(queue));
		node->data = data[count++];
		node->nextptr = NULL;
		temp->nextptr = node;
		temp = temp->nextptr;
	}
	
	clock_t ending_time = clock();

	return (double)(ending_time - starting_time) / CLOCKS_PER_SEC * 1000;
}


double fill_bst(bst **bst_, int data[20]){
	clock_t starting_time = clock();
	
	int count = 0;
	int x, direction;
	bst *temp;

	*bst_ = (bst*)malloc(sizeof(bst));
	(*bst_)->data = data[count++];
	(*bst_)->left = NULL;
	(*bst_)->right = NULL;
	for( x = 1 ; x != 20; x++){
		temp = *bst_;
		count = 0;
		while( count != 30 && temp->data != data[x]){
			if( temp->data > data[x]){
				if(temp->left == NULL){
					direction = 0;
					break;
				}
				temp = temp->left;				
			}
			else if( temp->data < data[x] ){
				if(temp->right == NULL){
					direction = 1;
					break;
				}
				temp = temp->right;				
			}
			count++;
		}
		if( direction == 0 && temp->data != data[x] ){
			temp->left = (bst*)malloc(sizeof(bst));
			temp = temp->left;		
		}
		else if(direction == 1 && temp->data != data[x]){
			temp->right = (bst*)malloc(sizeof(bst));
			temp = temp->right;		
		}
		if(temp->data != data[x]){
			temp->data = data[x];
			temp->left = NULL;
			temp->right = NULL;
		}		
	}

	clock_t ending_time = clock();
	return (double)( ending_time - starting_time ) / CLOCKS_PER_SEC * 1000;
}

void print_time(double stack_time, double queue_time, double bst_time){
	printf("Structures\tStack\tQueue\tBST\n");
	printf("Exec. Time\t%.3f\t%.3f\t%.3f\n", stack_time, queue_time, bst_time);
}

void fill_structures(stack ** stack_, queue ** queue_, bst ** bst_, int data[20]){
	double stack_time, queue_time, bst_time;

	stack_time = fill_stack(stack_, data);
	queue_time = fill_queue(queue_, data);
	bst_time = fill_bst(bst_, data);
	print_time(stack_time, queue_time, bst_time);
	
}
void ordered_stack(stack *new, stack *node){
	stack *temp;
	if( new == NULL){
		new = (stack*)malloc(sizeof(stack));
		new->data = node->data;
		new->nextptr = NULL;
	}
	else{
		temp = (stack*)malloc(sizeof(stack));
		temp->data = node->data;
		temp->nextptr = new;
		new = temp;
	}
}

void print_tree(bst *root){

	if( root == NULL){
		return;
	}
	print_tree(root->right);
	printf("%d ", root->data);
	print_tree(root->left);
		
}


void ordered_print(stack * stack_, queue * queue_, bst * bst_){
	//Stack
	double stack_time, queue_time, bst_time;
	int x = 0;
	int i,j, max;
	int val;
	int data_s[20],data_q[20];
	stack *temp1, *temp2, *new, *temp3, *point;
	queue *t1, *t2, *max1;
	temp1 = stack_;

	clock_t start = clock();
	temp1 = stack_;
	while( temp1 != NULL ){
		data_s[x++] = temp1->data;
		temp1 = temp1->nextptr;
	}
	for( i = 0 ; i < 20; i++){
		
		for( j = i ; j < 20 ; j++){
			if( data_s[i] < data_s[j]){
				val = data_s[i];
				data_s[i] = data_s[j];
				data_s[j] = val;
			}
		}
	}
	
	printf("Descing order in stack:\n");
	i = 0;
	while( i < 20 ){
		printf("%d ", data_s[i++]);
		
	}
	printf("\n");
	clock_t end = clock();
	stack_time = (double)(end - start) / CLOCKS_PER_SEC * 1000;
	printf("Order time for stack: %f\n", stack_time);

	//Queue
	clock_t start1 = clock();
	t1 = queue_;
	x = 0;
	while( t1 != NULL ){
		data_q[x++] = t1->data;
		t1 = t1->nextptr;
	}
	for( i = 0 ; i < 20; i++){
		
		for( j = i ; j < 20 ; j++){
			if( data_q[i] < data_q[j]){
				val = data_q[i];
				data_q[i] = data_q[j];
				data_q[j] = val;
			}
		}
	}
	
	printf("Descing order in queue:\n");
	i = 0;
	while( i < 20 ){
		printf("%d ", data_q[i++]);
		
	}
	printf("\n");
	clock_t end1 = clock();
	queue_time = (double)(end1 - start1) / CLOCKS_PER_SEC * 1000;
	printf("Order time for queue: %f\n", queue_time);



	clock_t start2 =clock();
	printf("Descing order in binary tree:\n");
	print_tree(bst_);
	clock_t end2 = clock();
	bst_time = (double)(end2 - start2) / CLOCKS_PER_SEC * 1000;
	printf("\nOrder time for binary search tree: %f\n", bst_time);


}

void search_tree(bst *tree, int value, int step){
	if( tree == NULL){
		return;
	}
	if( tree->data == value){
		printf("%d result(s) founded on %d. step\n", 1, step);
		return;
	}
	else if( tree->data > value ){
		search_tree(tree->left, value, step+1);
	}
	else if( tree->data < value ){
		search_tree(tree->right, value, step+1);
	}

}

void search(stack * stack_, queue * queue_, bst * bst_, int val_to_search){
	stack *stc = stack_;
	queue *que = queue_;
	bst *tree = bst_;
	int a = 0, b = 0, c = 0;
	int total = 0;

	clock_t start = clock();
	while( stc != NULL ){
		a++;
		if( stc->data == val_to_search){
			printf("%d result(s) founded on %d. step\n", total+1, a);
			total++;
		}
		stc = stc->nextptr;
	}
	total = 0;
	clock_t end = clock();
	printf("Search stack exe. time: %.3f\n", (double)(end-start)/CLOCKS_PER_SEC*1000);

	
	start = clock();
	while( que != NULL ){
		b++;
		if( que->data == val_to_search){
			printf("%d result(s) founded on %d. step\n", total+1, b);
			total++;
		}
		que = que->nextptr;
	}
	total = 0;
	end = clock();
	printf("Search queue exe. time: %.3f\n", (double)(end-start)/CLOCKS_PER_SEC*1000);


	start = clock();
	search_tree(tree,val_to_search,0);
	end = clock();
	printf("Search binary tree exe. time: %.3f\n", (double)(end-start)/CLOCKS_PER_SEC*1000);

}



void special_traverse(stack * stack_, queue * queue_, bst * bst_){
	stack *temp1, *temp2, *min, *max;
	queue *t1, *t2, *az, *cok;
	int value, store[20];
	int dec = 0;

	clock_t start = clock();
	for( temp1 = stack_ ; temp1 != NULL; temp1 = temp1->nextptr ){
		if( dec % 2 == 1){
			min = temp1;
			for( temp2 = temp1; temp2 != NULL; temp2 = temp2->nextptr){
				if( min->data > temp2->data){
					min = temp2;
				}
			}
			value = min->data;
			min->data = temp1->data;
			temp1->data = value; 
			dec++;
		}
		else if( dec % 2 == 0){
			max = temp1;
			for( temp2 = temp1; temp2 != NULL; temp2 = temp2->nextptr){
				if( max->data < temp2->data){
					max = temp2;
				}
			}
			value = max->data;
			max->data = temp1->data;
			temp1->data = value; 
			dec++;
		}	
	}
	temp1 = stack_;
	printf("Special order for stack->\n");
	while(temp1 != NULL){
		printf("%d ", temp1->data);
		temp1 = temp1->nextptr;
	}
	clock_t end = clock();
	printf("\tSpecial stack exe. time: %.3f\n", (double)(end-start)/CLOCKS_PER_SEC*1000);

	start = clock();
	for( t1 = queue_ ; t1 != NULL; t1 = t1->nextptr ){
		if( dec % 2 == 1){
			az = t1;
			for( t2 = t1; t2 != NULL; t2 = t2->nextptr){
				if( az->data > t2->data){
					az = t2;
				}
			}
			value = az->data;
			az->data = t1->data;
			t1->data = value; 
			dec++;
		}
		else if( dec % 2 == 0){
			cok = t1;
			for( t2 = t1; t2 != NULL; t2 = t2->nextptr){
				if( cok->data < t2->data){
					cok = t2;
				}
			}
			value = cok->data;
			cok->data = t1->data;
			t1->data = value; 
			dec++;
		}	
	}
	t1 = queue_;
	printf("Special order for queue->\n");
	while(t1 != NULL){
		printf("%d ", t1->data);
		t1 = t1->nextptr;
	}
	end = clock();
	printf("\tSpecial queue exe. time: %.3f\n", (double)(end-start)/CLOCKS_PER_SEC*1000);

}

int main(){
	int data[20]= { 5, 2, 7, 8, 11, 3, 21, 7, 6, 15, 19, 35, 24, 1, 8, 12, 17, 8, 23, 4};
	bst * bst_;
	queue * queue_;
	stack * stack_;
	queue *temp;
	fill_structures(&stack_, &queue_, &bst_, data);
	ordered_print(stack_, queue_, bst_);
	search(stack_, queue_, bst_, 5);
	special_traverse(stack_, queue_, bst_);
	return 0;
}
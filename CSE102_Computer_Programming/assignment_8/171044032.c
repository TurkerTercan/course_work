//      TURKER  TERCAN      171044032       HW 8        CSE102          //

#include <stdio.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
#define PROPERTY_COUNT 13
typedef enum block_type{start=0,property,fortune,tax,punish}b_type;
typedef enum player_type{none=-1,car,cap}player_type;

typedef struct player
{
	player_type type;
	int current_block_id;
	int owned_blocks[PROPERTY_COUNT];
	int account;
	int turn_to_wait;
	char *player_name;
} player;

typedef struct block
{
    int id;
    char * name;
    int price;
    int rent;
    int rent_1;
    int rent_2;
    int rent_3;
    int house_price;
    int h_count;
    player owner;
    b_type type;
    struct block *ptr;
} block;

int roll_dice()
{

    int i=rand()%6+1;
    return i;
}
block * find_board(block *board, int n){
    block *temp;
    temp = board;
    for(int i = 0; i < n; i++){
        temp = temp->ptr;
    }
    return temp;
}

void show_property_deed(block *board)
{
    int exit_flag=0,i,selection;
    while (exit_flag==0)
    {
        printf("Please select a property to see details:\n");
        for (i = 0; i < 24; ++i) {
            if (find_board(board,i)->type==property)
                printf("%d -\t%s \n",find_board(board,i)->id,find_board(board,i)->name);
        }
        printf("0 -\tExit\n");
        scanf("%d",&selection);
        if (selection==0)
            exit_flag=1;
        else {
            if (selection > 0 && selection < 24 && find_board(board,selection)->type == property) {
                printf("\n\n---------------------------------\n");
                if (strlen(find_board(board,selection)->name) < 8)
                    printf("|\t\t%s\t\t|\n", find_board(board,selection)->name);
                else
                    printf("|\t    %s\t\t|\n", find_board(board,selection)->name);
                printf("---------------------------------\n");
                printf("|\tRent \t\t%d$\t|\n", find_board(board,selection)->rent);
                printf("|\tRent 1 H \t%d$\t|\n", find_board(board,selection)->rent_1);
                printf("|\tRent 2 H \t%d$\t|\n", find_board(board,selection)->rent_2);
                printf("|\tRent 3 H \t%d$\t|\n", find_board(board,selection)->rent_3);
                printf("---------------------------------\n");
                printf("|\tHouse Price \t%d$\t|\n", find_board(board,selection)->house_price);
                printf("|\tHouse count \t%d\t|\n", find_board(board,selection)->h_count);
                printf("---------------------------------\n\n\n");
            } else printf("\nWrong Selection! Please Try Again.\n\n");
        }
    }
}



void show_board(block *board, player player_one, player player_two)
{
	printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	int i=0,space_flag=0;
	for (i=0;i<7;i++)
	{
        if (strlen(find_board(board,i)->name)<8)
		    printf("|\t%s\t\t",find_board(board,i)->name);
        else
            printf("|\t%s\t",find_board(board,i)->name);
	}
	printf("|\n");
	for (i=0;i<7;i++)
	{
		if (find_board(board,i)->type==property || find_board(board,i)->type==tax)
		    printf("|\t%d$ \t\t",find_board(board,i)->price);
		else if (strlen(find_board(board,i)->name)<8)
            printf("|\t     \t\t");
        else
            printf("|\t\t \t");
	}
    printf("|\n");
    for (i=0;i<7;i++)
    {
        space_flag=0;
        printf("|\t");
        if (i==player_one.current_block_id && i==player_two.current_block_id)
        {
            printf("%s, %s \t",player_one.player_name,player_two.player_name);
            space_flag=1;
        }
        else {
            if (i == player_one.current_block_id) {
                printf("%s \t\t", player_one.player_name);
                space_flag = 1;
            }
            if (i == player_two.current_block_id) {
                printf("%s \t\t", player_two.player_name);
                space_flag = 1;
            }
        }
        if (space_flag==0) {
            if (strlen(find_board(board,i)->name)<8)
                printf("     \t\t");
            else
                printf(" \t\t");
        }
    }
    printf("|\n");
    printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

    //Middle
    for (i=0;i<5;i++)
    {
        //Names

        if (strlen(find_board(board,23-i)->name)<8)
            printf("|\t%s\t\t|",find_board(board,23-i)->name);
        else
            printf("|\t%s\t|",find_board(board,23-i)->name);
        printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");

        if (strlen(find_board(board,7+i)->name)<8)
            printf("|\t%s\t\t|",find_board(board,7+i)->name);
        else
            printf("|\t%s\t|",find_board(board,7+i)->name);
        printf("\n");
        //prices
        if (find_board(board,23-i)->type==property || find_board(board,23-i)->type==tax)
            printf("|\t%d$ \t\t|",find_board(board,23-i)->price);
        else if (strlen(find_board(board,23-i)->name)<8)
            printf("|\t     \t|");
        else
            printf("|\t\t \t|");


        printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");

        if (find_board(board,7+i)->type==property || find_board(board,7+i)->type==tax)
            printf("|\t%d$ \t\t",find_board(board,7+i)->price);
        else if (strlen(find_board(board,7+i)->name)<8)
            printf("|\t     \t");
        else
            printf("|\t\t \t");

        printf("|\n");
        //spaces

        space_flag=0;
        printf("|\t");
        if (23-i==player_one.current_block_id && 23-i==player_two.current_block_id)
        {
            printf("%s, %s \t|",player_one.player_name,player_two.player_name);
            space_flag=1;
        }
        else {
            if (23 - i == player_one.current_block_id) {
                printf("%s \t\t|", player_one.player_name);
                space_flag = 1;
            }
            if (23 - i == player_two.current_block_id) {
                printf("%s \t\t|", player_two.player_name);
                space_flag = 1;
            }
        }
        if (space_flag==0) {
            if (strlen(find_board(board,23-i)->name)<8)
                printf("\t\t|");
            else
                printf("    \t\t|");
        }

        printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        space_flag=0;
        printf("|\t");
        if (7+i==player_one.current_block_id && 7+i==player_two.current_block_id)
        {
            printf("%s, %s \t",player_one.player_name,player_two.player_name);
            space_flag=1;
        }
        else {

            if (7+i==player_one.current_block_id)
            {
                printf("%s \t\t",player_one.player_name);
                space_flag=1;
            }
            if (7+i==player_two.current_block_id)
            {
                printf("%s \t\t",player_two.player_name);
                space_flag=1;
            }
        }

        if (space_flag==0) {
            if (strlen(find_board(board,7+i)->name)<8)
                printf("\t\t");
            else
                printf("    \t\t");
        }

        printf("|\n");
        if (i!=4) {
            printf("-------------------------");
            printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            printf("-------------------------");
            printf("\n");
        }

    }

    //bottom row
    printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    for (i=18;i>=12;i--)
    {
        if (strlen(find_board(board,i)->name)<8)
            printf("|\t%s\t\t",find_board(board,i)->name);
        else
            printf("|\t%s\t",find_board(board,i)->name);
    }
    printf("|\n");
    for (i=18;i>=12;i--)
    {
        if (find_board(board,i)->type==property || find_board(board,i)->type==tax)
            printf("|\t%d$ \t\t",find_board(board,i)->price);
        else if (strlen(find_board(board,i)->name)<8)
            printf("|\t     \t\t");
        else
            printf("|\t\t \t");
    }
    printf("|\n");
    for (i=18;i>=12;i--)
    {
        space_flag=0;
        printf("|\t");
        if (i==player_one.current_block_id && i==player_two.current_block_id)
        {
            printf("%s, %s \t",player_one.player_name,player_two.player_name);
            space_flag=1;
        }
        else {
            if (i == player_one.current_block_id) {
                printf("%s \t\t", player_one.player_name);
                space_flag = 1;
            }
            if (i == player_two.current_block_id) {
                printf("%s \t\t", player_two.player_name);
                space_flag = 1;
            }
        }
        if (space_flag==0) {
            if (strlen(find_board(board,i)->name)<8)
                printf("     \t\t");
            else
                printf(" \t\t");
        }

    }
    printf("|\n");
    printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
}



void init_board(block *board)
{
    block *temp;
    block *node;
    player temp_player;
    temp_player.type=none;

    board->name="Start";
    board->id = 0;
    board->price=0;
    board->rent=0;
    board->rent_1=0;
    board->rent_2=0;
    board->rent_3=0;
    board->owner=temp_player;
    board->h_count=0;
    board->house_price = 0;
    board->type=start;
    board->ptr = NULL;
    temp = board;

    node = (block*)malloc(sizeof(block));
    node->name="Esenyurt";
    node->id = 1;
    node->price=16000;
    node->rent=800;
    node->rent_1=4000;
    node->rent_2=9000;
    node->rent_3=25000;
    node->owner=temp_player;
    node->house_price=10000;
    node->h_count=0;
    node->type = property;
    node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->type=tax;
    node->name="Car park";
    node->id=2;
    node->price=1500;
    node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->type = fortune;
    node->name ="Fortune Card";
    node->id = 3;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->id=4;
    node->name="Tuzla";
    node->price=16500;
    node->rent=850;
    node->rent_1=4250;
    node->rent_2=9500;
    node->rent_3=26000;
    node->house_price=12000;
    node->h_count=0;
    node->owner=temp_player;
    node->type = property;
    node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->id=5;
    node->name="Arnavutkoy";
    node->price=17000;
    node->rent=850;
    node->rent_1=4500;
    node->rent_2=10000;
    node->rent_3=28000;
    node->owner=temp_player;
    node->house_price=12000;
    node->h_count=0;
    node->type = property;
    node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->type=punish;
    node->name="Wait 2 turn";
    node->price=2;
    node->id=6;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->id=7;
    node->name="Catalca";
    node->price=20000;
    node->rent=1000;
    node->rent_1=5000;
    node->rent_2=12000;
    node->rent_3=30000;
    node->house_price=13000;
    node->h_count=0;
    node->owner=temp_player;
    node->type = property;
    node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->id=8;
    node->name="Beykoz";
    node->price=23000;
    node->rent=1100;
    node->rent_1=5500;
    node->rent_2=12500;
    node->rent_3=33000;
    node->house_price=13000;
    node->owner=temp_player;
    node->h_count=0;
    node->type = property;
    node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->type = fortune;
    node->name ="Fortune Card";
    node->id = 9;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->name="Car Fix";
    node->id=10;
    node->price=1750;
    node->type = tax;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->id=11;
    node->name="Maltepe";
    node->price=30000;
    node->rent=1350;
    node->owner=temp_player;
    node->rent_1=7000;
    node->rent_2=15000;
    node->rent_3=40000;
    node->house_price=15000;
    node->h_count=0;
    node->type= property;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->name="Bills";
    node->id=12;
    node->price=2000;
    node->type = tax;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->id=13;
    node->name="Sisli";
    node->price=33000;
    node->rent=1500;
    node->rent_1=8000;
    node->owner=temp_player;
    node->rent_2=16000;
    node->rent_3=42000;
    node->house_price=16000;
    node->h_count=0;
    node->type = property;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->type = tax;
    node->name="Oil";
    node->id=14;
    node->price=2250;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->type = fortune;
    node->name ="Fortune Card";
    node->id = 15;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->id=16;
    node->name="Atasehir";
    node->price=35000;
    node->rent=1600;
    node->rent_1=8500;
    node->rent_2=17000;
    node->rent_3=45000;
    node->house_price=17000;
    node->owner=temp_player;
    node->h_count=0;
    node->type = property;
    node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->id=17;
    node->name="Sariyer";
    node->price=40000;
    node->rent=1750;
    node->rent_1=9500;
    node->rent_2=19000;
    node->rent_3=48000;
    node->owner=temp_player;
    node->house_price=19000;
    node->h_count=0;
    node->type = property;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->type= punish;
    node->name="Wait 1 turn";
    node->price=1;
    node->id=18;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->id=19;
    node->name="Kadikoy";
    node->price=43000;
    node->rent=1900;
    node->rent_1=11000;
    node->rent_2=21500;
    node->rent_3=55000;
    node->owner=temp_player;
    node->house_price=23000;
    node->h_count=0;
    node->type= property;
    node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->id=20;
    node->name="Besiktas";
    node->price=60000;
    node->rent=2500;
    node->rent_1=15000;
    node->rent_2=28000;
    node->rent_3=60000;
    node->owner=temp_player;
    node->house_price=30000;
    node->h_count=0;
    node->type= property;
    node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->type = fortune;
    node->name ="Fortune Card";
    node->id = 21;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->type = tax;
    node->name="Vocation";
    node->id=22;
    node->price=5000;
     node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));

    node->id=23;
    node->name="Bebek";
    node->price=70000;
    node->rent=3500;
    node->rent_1=20000;
    node->rent_2=35500;
    node->owner=temp_player;
    node->rent_3=65000;
    node->house_price=35000;
    node->h_count=0;
    node->type=property;
    node->ptr = NULL;
    temp->ptr = node;
    temp = temp->ptr;
    node = (block*)malloc(sizeof(block));
    
}
void show_player_properties(block *board, player current_player)
{
    int i,counter=0,selection;
    while (selection!=0) {
        printf("----------------------------------------\n");
        for (i = 0; i < PROPERTY_COUNT; i++) {
            if (current_player.owned_blocks[i] != -1) {
                printf("%d - %s\n", find_board(board,current_player.owned_blocks[i])->id,
                       find_board(board,current_player.owned_blocks[i])->name);
                counter++;
            }

        }
        if (counter == 0)
        {
            printf("You have not owned any property yet.\n");
            printf("----------------------------------------\n");
            selection=0;
        }
        else
        {

            printf("0 - Back \n");
            printf("----------------------------------------\n");
            printf("Please select an property to see details.\n");
            scanf("%d", &selection);

            if (selection!=0) {
                if (find_board(board,selection)->owner.type != current_player.type)
                    printf("You made a wrong selection!\n");
                else {
                    printf("\n\n---------------------------------\n");
                    if (strlen(find_board(board,selection)->name) < 8)
                        printf("|\t\t%s\t\t|\n", find_board(board,selection)->name);
                    else
                        printf("|\t    %s\t\t|\n", find_board(board,selection)->name);
                    printf("---------------------------------\n");
                    printf("|\tRent \t\t%d$\t|\n", find_board(board,selection)->rent);
                    printf("|\tRent 1 H \t%d$\t|\n", find_board(board,selection)->rent_1);
                    printf("|\tRent 2 H \t%d$\t|\n", find_board(board,selection)->rent_2);
                    printf("|\tRent 3 H \t%d$\t|\n", find_board(board,selection)->rent_3);
                    printf("---------------------------------\n");
                    printf("|\tHouse Price \t%d$\t|\n", find_board(board,selection)->house_price);
                    printf("|\tHouse count \t%d\t|\n", find_board(board,selection)->h_count);
                    printf("---------------------------------\n\n\n");
                }
            }
        }
    }
}

int build_house(block* current_block, player* current_player){
    int flag=0;
    if (current_player->type==current_block->owner.type)
    {
        if (current_block->h_count<3)
        {
            if (current_block->house_price<=current_player->account)
            {
                flag=1;
                current_player->account-=current_block->house_price;
                current_block->h_count+=1;
            }
            else
                printf("You don't have enough money to build house!\n");
        }
        else
            printf("You can't buy more than 3 houses in a property!\n");
    }
    else
        printf("You don't have permission to build house on this property!\n ");
    return flag;
}

void build_ai_house(block* current_block, player* ai){
    if (ai->type==current_block->owner.type)
    {
        if (current_block->h_count<3)
        {
            if (current_block->house_price<=ai->account)
            {
                ai->account-=current_block->house_price;
                current_block->h_count+=1;
            }
            else
                printf("AI does not have enough money to build a house!\n");
        }
        else
            printf("AI can't buy more than 3 houses in a property!\n");
    }
    else
        printf("AI doesn't have permission to build house on this property!\n ");
}


int count_properties(player current_player)
{
    int i,count=0;
    for (i=0;i<PROPERTY_COUNT;i++)
    {
        if (current_player.owned_blocks[i]!=-1)
            count++;
    }
    return count;
}

void sell_property(block *board, player* current_player)
{
    int property_id,flag=0,is_owned=-1,i,index,counter=0;
        if (count_properties(*current_player)!=0)
        {
            for (i = 0; i < PROPERTY_COUNT; i++) {
                if (current_player->owned_blocks[i] != -1) {
                    printf("%d - %s\n", find_board(board,current_player->owned_blocks[i])->id, find_board(board,current_player->owned_blocks[i])->name);
                    counter++;
                }

            }
            printf("0 - Back\n");
            printf("Please select to sell the property:\n");

            scanf("%d",&property_id);
            if (property_id!=0)
            {
                for(i=0;i<PROPERTY_COUNT;i++)
                {
                    if (current_player->owned_blocks[i]==property_id)
                    {
                        is_owned=1;
                        index=i;
                        break;
                    }
                }
                if(is_owned!=-1)
                {
                    current_player->owned_blocks[index]=-1;
                    player temp;
                    find_board(board,property_id)->owner.type=none;
                    if(find_board(board,property_id)->h_count==0)current_player->account+=find_board(board,property_id)->price/2;
                    if(find_board(board,property_id)->h_count==1)
                    {
                        current_player->account+=(find_board(board,property_id)->price+find_board(board,property_id)->house_price)/2;
                    }
                    if(find_board(board,property_id)->h_count==2)
                    {
                        current_player->account+=(find_board(board,property_id)->price+(2*find_board(board,property_id)->house_price))/2;
                    }
                    if(find_board(board,property_id)->h_count==3)
                    {
                        current_player->account+=(find_board(board,property_id)->price+(3*find_board(board,property_id)->house_price))/2;
                    }
                }
            }
        } 
        else{
            printf("You don't have any property to sell.\n");
        }

}

int insufficient_fund(block *board, player* current_player,int required_money){
    int selection,flag=1;
    while (selection!=4 && current_player->account<required_money && count_properties(*current_player)!=0)
    {
        printf("You don't have enough money to continue your transaction.You need %d$ more. Please select an option to continue: \n",required_money-current_player->account);
        printf("1- Sell Property\n");
        printf("2- Show my Properties\n");
        printf("3- Show my Account\n");
        printf("0- Back\n");
        scanf("%d",&selection);
        switch (selection)
        {
            case 1:
                sell_property(board,current_player);
                break;
            case 2:
                show_player_properties(board,*current_player);
                break;
            case 3:
                printf("\n\nThere are %d $ in your account.\n\n",current_player->account);
                break;
            case 0:
                flag=0;
                break;
            default:
                printf("You made a wrong selection!\n");
                break;

        }
    }
    return flag;
}
void ai_sell_property(block *board, player *current_player){
    int property_id = -1,flag=0,is_owned=-1,i,index,counter=0;

    if (count_properties(*current_player)!=0)
    {   
        property_id = current_player->owned_blocks[0];
        for(int i = 0 ; i < PROPERTY_COUNT; i++){
            if( current_player->owned_blocks[i] != -1){
                if( current_player->owned_blocks[i] > property_id){
                    property_id = current_player->owned_blocks[i];
                    index = i;
                }
            }
        }

        if(property_id != -1){
            current_player->owned_blocks[index]=-1;
            find_board(board,property_id)->owner.type=none;
            if(find_board(board,property_id)->h_count==0)current_player->account+=find_board(board,property_id)->price/2;
            if(find_board(board,property_id)->h_count==1)
            {
                current_player->account+=(find_board(board,property_id)->price+find_board(board,property_id)->house_price)/2;
            }
            if(find_board(board,property_id)->h_count==2)
            {
                current_player->account+=(find_board(board,property_id)->price+(2*find_board(board,property_id)->house_price))/2;
            }
            if(find_board(board,property_id)->h_count==3)
            {
                current_player->account+=(find_board(board,property_id)->price+(3*find_board(board,property_id)->house_price))/2;
            }
        }        
    } 
    else{
        printf("AI doesn`t have any property to sell.\n");
    }
}

void ai_insufficient_fund( block *board, player* current_player,int required_money ){
    while ( current_player->account<required_money && count_properties(*current_player)!=0){
        ai_sell_property(board,current_player);
    }
}

void ai_rent(block *board, player* current_player,player * owner){
    int rent=0,owned_property_count;
    rent=find_board(board,current_player->current_block_id)->rent;
    if (find_board(board,current_player->current_block_id)->h_count==1)rent=find_board(board,current_player->current_block_id)->rent_1;
    if (find_board(board,current_player->current_block_id)->h_count==2)rent=find_board(board,current_player->current_block_id)->rent_2;
    if (find_board(board,current_player->current_block_id)->h_count==3)rent=find_board(board,current_player->current_block_id)->rent_3;
    if (rent>current_player->account)
    {
        owned_property_count=count_properties(*current_player);
        while(rent>current_player->account && owned_property_count!=0)
        {
            ai_insufficient_fund(board,current_player,rent);
            owned_property_count=count_properties(*current_player);
        }
    }
    if (rent<current_player->account)
    {
        current_player->account-=rent;
        owner->account+=rent;
    } else
        current_player->account=0;
}

void pay_rent(block *board, player* current_player,player * owner){
    int rent=0,owned_property_count;
    rent=find_board(board,current_player->current_block_id)->rent;
    if (find_board(board,current_player->current_block_id)->h_count==1)rent=find_board(board,current_player->current_block_id)->rent_1;
    if (find_board(board,current_player->current_block_id)->h_count==2)rent=find_board(board,current_player->current_block_id)->rent_2;
    if (find_board(board,current_player->current_block_id)->h_count==3)rent=find_board(board,current_player->current_block_id)->rent_3;
    if (rent>current_player->account)
    {
        owned_property_count=count_properties(*current_player);
        while(rent>current_player->account && owned_property_count!=0)
        {
            insufficient_fund(board,current_player,rent);
            owned_property_count=count_properties(*current_player);
        }
    }
    if (rent<current_player->account)
    {
        current_player->account-=rent;
        owner->account+=rent;
    } else
        current_player->account=0;
}

void add_to_owned(player* current_player){
    for (int i = 0; i < PROPERTY_COUNT; ++i) {
        if (current_player->owned_blocks[i]==-1) {
            current_player->owned_blocks[i] = current_player->current_block_id;
            printf("Current block id %d\n",current_player->current_block_id);
            break;
        } else
            printf("%d ",current_player->owned_blocks[i]);
    }
    printf("\n");
}

void copy_owner(player* old, player* new){
    old->type=new->type;
    old->account=new->account;
    old->turn_to_wait=new->turn_to_wait;
    old->player_name=new->player_name;
    old->current_block_id=new->current_block_id;
    for (int i = 0; i < PROPERTY_COUNT; ++i) {
        old->owned_blocks[i]=new->owned_blocks[i];
    }
}

void buy_property(block* current_block, player* current_player){
    if (current_block->price<=current_player->account)
    {
        current_player->account-=current_block->price;
        copy_owner(&current_block->owner,current_player);
        add_to_owned(current_player);

        printf("Congratiulations! You have buy %s.\n",current_block->name);
        printf("You left %d$ in your account.\n",current_player->account);

    }
    else
        printf("You don't have enough money to buy this property!\n");
}

void buy_ai_property(block *current_block, player* ai){
    if (current_block->price<=ai->account)
    {
        ai->account-=current_block->price;
        copy_owner(&current_block->owner,ai);
        add_to_owned(ai);

        printf("AI has bought %s.\n",current_block->name);
        printf("AI left %d$ in its account.\n",ai->account);

    }
    else
        printf("AI doesn`t have enough money to buy this property!\n");
}

void house_dialog(block *board, player* current_player){
    int house_flag=1,selection,insuf_flag=1,build_flag=1;
    while (house_flag && build_flag) {
        printf("Do you want to build house on %s ?\n",
               find_board(board,current_player->current_block_id)->name);
        printf("1- Yes\n");
        printf("2- No\n");
        scanf("%d", &selection);
        if (selection == 1) {

            if (current_player->account < find_board(board,current_player->current_block_id)->house_price) {
                while (count_properties(*current_player)!=0 && current_player->account<find_board(board,current_player->current_block_id)->house_price && insuf_flag==1)
                    insuf_flag=insufficient_fund(board,current_player,find_board(board,current_player->current_block_id)->house_price);
            }
            if (current_player->account >= find_board(board,current_player->current_block_id)->house_price)
                build_flag=build_house(find_board(board,current_player->current_block_id), current_player);
            else
                house_flag=0;


        } else
            house_flag=0;
    }
}

void free_house(block *board, player *current){
    int i = count_properties(*current);    
    int k,x;
    if( i != 0){
        if (current->type == car){
            printf("Where do you want to build a house ?\n");
            for(int a = 0; a < i ; a++){
                printf("%d - %s", a+1, find_board(board,a)->name);
            }
            scanf("%d",&x);
            x = x -1;
            if(find_board(board,x)->h_count != 3){
                find_board(board,x)->h_count = find_board(board,x)->h_count + 1;
            }
            else
                printf("You can not build more than 3 houses in a property.\n");
        }
        else{
            k = rand() % (i + 1);
            if(find_board(board,k)->h_count != 3){
                find_board(board,k)->h_count = find_board(board,k)->h_count + 1;
            }
            else
                printf("You can not build more than 3 houses in a property.\n");
        }
    }
    else
        printf("The player does not have any properties to build a house.\n");    
}

void time_travel(player *current){
    int i = roll_dice();
    if( i <= 3 && i >= 1){
        printf("The player has moved foward 2 blocks.\n");
        current->current_block_id = current->current_block_id + 2;
    }
    else{
        printf("The player has moved backward 2 blocks.\n");
        current->current_block_id = current->current_block_id - 2;
    }
}

void after_dice_menu(block *board, player* current_player, player* other){
    int card;
    int selection,insuf_flag=1;
    switch (find_board(board,current_player->current_block_id)->type)
    {
        case property:
            printf("%s has arrived %s \n",current_player->player_name,find_board(board,current_player->current_block_id)->name);
            if (find_board(board,current_player->current_block_id)->owner.type==none)
            {
                printf("Do you want to buy %s ?\n",find_board(board,current_player->current_block_id)->name);
                printf("1- Yes\n");
                printf("2- No\n");
                scanf("%d",&selection);
                if (selection==1)
                {

                    if (current_player->account<find_board(board,current_player->current_block_id)->price)
                    {
                        while (count_properties(*current_player)!=0 && current_player->account<find_board(board,current_player->current_block_id)->price && insuf_flag==1 )
                        {
                            insuf_flag=insufficient_fund(board,current_player,find_board(board,current_player->current_block_id)->price);
                        }
                    }
                    buy_property(find_board(board,current_player->current_block_id),current_player);
                    if (current_player->type==find_board(board,current_player->current_block_id)->owner.type)
                    {
                        house_dialog(board,current_player);
                    }

                }

            } 
            else
            {
                pay_rent(board,current_player,other);
            }
            break;
        case tax:
            printf("%s has arrived tax block(%s)\n",current_player->player_name,find_board(board,current_player->current_block_id)->name);
            while (count_properties(*current_player)!=0 && current_player->account<find_board(board,current_player->current_block_id)->price) {
                insuf_flag = insufficient_fund(board, current_player,
                find_board(board,current_player->current_block_id)->price);
                if (insuf_flag == 0 && current_player->account < find_board(board,current_player->current_block_id)->price)
                    printf("You must pay your tax!\n");
            }
            if (current_player->account>find_board(board,current_player->current_block_id)->price)
                current_player->account=current_player->account-find_board(board,current_player->current_block_id)->price;
            else
            {
                current_player->account=0;
            }
            break;
        case punish:
            printf("%s has punished for %d turn. \n",current_player->player_name,find_board(board,current_player->current_block_id)->price);
            current_player->turn_to_wait+=find_board(board,current_player->current_block_id)->price;
            break;
        case fortune:
            card = rand()%5 + 1;
            switch(card){
                case 1: printf("Fortune Card : Free House\n"); free_house(board,current_player); break;
                case 2: printf("Fortune Card : Time Travel\n"); time_travel(current_player); break;
                case 3: printf("Fortune Card : Garnishment\nYou have been paid 5000$ to the bank.\n"); current_player->account -= 5000; break;
                case 4: printf("Fortune Card : Generosity\nYou have been paid 10000$ to your opponent\n"); current_player->account -= 10000; other->account += 10000; break;
                case 5: printf("Fortune Card : Treasure Hunter\nYou have been taken 20000$ from the bank.\n"); current_player->account += 20000; break;
            }
            break;

    }
}

void turn(block *board, player* current_player, player * other){
    int flag=1,next_block;
    while(flag)
    {
        int selection;
        printf("1 - Roll the dice\n");
        printf("2 - Show my account\n");
        printf("3 - Show my properties\n");
        printf("4 - Show property deeds\n");
        printf("5 - Buy Property\n");
        printf("6 - Buy house \n");
        printf("7 - Sell property\n");
        printf("Please select an option to continue (%s):\n",current_player->player_name);
        scanf("%d",&selection);
        switch(selection)
        {
            case 1:
                if (current_player->turn_to_wait==0) {
                    next_block=(current_player->current_block_id + roll_dice());
                    if (next_block>=24)current_player->account+=10000;
                    current_player->current_block_id = next_block % 24;
                    after_dice_menu(board,current_player,other);
                    flag = 0;
                } else
                {
                    printf("You need to wait %d turn to continue.\n",current_player->turn_to_wait);
                    current_player->turn_to_wait-=1;
                    flag=0;
                }
                break;
            case 2:
                printf("\n\nThere are %d $ in your account.\n\n",current_player->account);
                break;
            case 3:
                show_player_properties(board,*current_player);
                break;
            case 4:
                show_property_deed(board);
                break;
            case 5:

                if (find_board(board,current_player->current_block_id)->type==property && find_board(board,current_player->current_block_id)->owner.type==-1)
                buy_property(find_board(board,current_player->current_block_id),current_player);
                else
                {

                    printf("You don't have permission to buy this block!\n");
                }

                break;
            case 6:
                if (current_player->type==find_board(board,current_player->current_block_id)->owner.type) {
                    house_dialog(board,current_player);
                }else
                {
                    printf("You don't have permission to build house on this block!\n");
                }
                break;
            case 7:
                sell_property(board,current_player);
                break;
            default:
                printf("Wrong selection!\n");
        }
    }
}
void after_ai_dice( block *board, player* ai, player * human ){
    int i, costs = 0,count = 0, dice, x = 0,card;
    for( i = 0 ; i < 24; i++){
        costs += find_board(board,i)->price;
        if( find_board(board,i)->type == property){
            count++;
        }
    }
    costs = costs / count;
    if( find_board(board,ai->current_block_id)->type == property){
        if( find_board(board,ai->current_block_id)->owner.type == none ){
            if( find_board(board,ai->current_block_id)->price <= costs ){
                buy_ai_property( find_board(board,ai->current_block_id), ai);
            }
            else{
                dice = roll_dice();
                if( dice <= 3 && dice >= 1){
                    buy_ai_property( find_board(board,ai->current_block_id), ai);
                }
                else
                    return;
            }
        }
        else if( find_board(board,ai->current_block_id)->owner.type == ai->type){
            for( i = 0 ; i < 13 ; i++){
                if( ai->owned_blocks[i] <= 23 && ai->owned_blocks[i] >= 0){
                    x++;
                }
                else
                    break;
            }
            if( x == PROPERTY_COUNT / 3){
                dice = roll_dice();
                if( dice <= 1 && dice >= 3 ){
                    build_ai_house( find_board(board,ai->current_block_id), ai);
                }
            }
            else
                return;
        }
        
        else{
            ai_rent(board,ai,human);
        }
    }
    else if( find_board(board,ai->current_block_id)->owner.type == fortune ){
            card = rand()%5 + 1;
            switch(card){
                case 1: printf("Fortune Card : Free House\n"); free_house(board,ai); break;
                case 2: printf("Fortune Card : Time Travel\n"); time_travel(ai); break;
                case 3: printf("Fortune Card : Garnishment\nYou have been paid 5000$ to the bank.\n"); ai->account -= 5000; break;
                case 4: printf("Fortune Card : Generosity\nYou have been paid 10000$ to your opponent\n"); ai->account -= 10000; human->account += 10000; break;
                case 5: printf("Fortune Card : Treasure Hunter\nYou have been taken 20000$ from the bank.\n"); ai->account += 20000; break;
            }

        }
    else if( find_board(board,ai->current_block_id)->type == tax ){
        printf("%s has arrived tax block(%s)\n",ai->player_name,find_board(board,ai->current_block_id)->name);
        while (count_properties(*ai)!=0 && ai->account<find_board(board,ai->current_block_id)->price) {
            ai_insufficient_fund(board, ai, find_board(board,ai->current_block_id)->price);
            if (ai->account < find_board(board,ai->current_block_id)->price)
                printf("You must pay your tax!\n");
        }
        if (ai->account>find_board(board,ai->current_block_id)->price)
            ai->account=ai->account-find_board(board,ai->current_block_id)->price;
        else{
            ai->account=0;
        }
    }
    else if( find_board(board,ai->current_block_id)->type == punish){
        ai->turn_to_wait += find_board(board,ai->current_block_id)->price;
        printf("AI needs to wait %d turn to continue.\n",ai->turn_to_wait);
    }
}

void ai_turn( block *board, player* ai, player * human ){
    int dice,next_block;
    dice = roll_dice();
    printf("“Computer rolled the dice: %d”\n", dice);
    if(ai->turn_to_wait==0) {
        next_block=(ai->current_block_id + dice);
        if(next_block>=24)ai->account+=10000;
        ai->current_block_id = next_block % 24;
        after_ai_dice( board, ai, human);
    }
    else{
        printf("AI needs to wait %d turn to continue.\n",ai->turn_to_wait);
        ai->turn_to_wait-=1;
    }
}

int check_end(player current_player){
    int owned_property_count=count_properties(current_player);
    if (current_player.account<=0 && owned_property_count==0)return 1;
    else return 0;
}

void play_game(block *board, player* player_one, player* player_two){
    int is_end=0;
    printf("Welcome to Monopoly! %s will roll the dice first.\n",player_one->player_name);
    while(is_end!=1)
    {
        printf("%s :%d$ , %s:%d$\n",player_one->player_name,player_one->account,player_two->player_name,player_two->account);
        ai_turn(board,player_one,player_two);
        show_board(board,*player_one,*player_two);
        is_end=check_end(*player_one);
        if (is_end==0)
        {
            printf("%s :%d$ , %s:%d$\n",player_one->player_name,player_one->account,player_two->player_name,player_two->account);
            turn(board,player_two,player_one);
            show_board(board,*player_one,*player_two);
            is_end=check_end(*player_two);
            if (is_end==1)
                printf("%s don't have enough money to payment, unfortunately %s lost the game!\n",player_two->player_name,player_two->player_name);
        } else{
            printf("%s doesn't have enough money to payment, unfortunately %s lost the game!\n",player_one->player_name,player_one->player_name);
        }

    }

}

void init_owned(player* current_player){
    int i;
    for (i=0;i<PROPERTY_COUNT;i++)
    {
        current_player->owned_blocks[i]=-1;
    }
}


int main() {
    block *board = (block*)malloc(sizeof(block));
    init_board(board);
    srand(time(NULL));
    player player_one,player_two;

    player_one.player_name="Cap";
	player_one.type=cap;
	player_one.current_block_id=0;
    player_one.turn_to_wait=0;
	player_one.account=100000;
    init_owned(&player_one);

	player_two.player_name="Car";
	player_two.type=car;
	player_two.current_block_id=0;
    player_two.turn_to_wait=0;
	player_two.account=100000;
    init_owned(&player_two);

    show_board(board,player_one,player_two);
    play_game(board,&player_one,&player_two);
    
    
    return 0;
}
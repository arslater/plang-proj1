#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char ** argv)
{
    unsigned short int RETRN_CODE = 0;
    char ** readLine    = (char **) malloc(sizeof(char) * 200);
    char * buff        = (char *) malloc(sizeof(char) * 64);
    char * regex       = (char *) malloc(sizeof(char) * 64);
    FILE * fp          = NULL;

    //////////////////////////////////////////////////////////
    // Only procede if valid number of arguments have been met
    if ( argc >= 3)
    {
        printf("%s\n",argv[1]);

        fp = fopen(argv[2], "r");
        if(fp == NULL)
        {
            ////////////////////////////
            // Erorr opening the provided file for reading
            fprintf(stderr,"[Error] Cannot open '%s' for reading. Exiting...\n", argv[2]);
            RETRN_CODE = 2;
        }
        else
        {
            //////////////////////////////////////
            // Valid file, commencing with program

            int i = 0;
            regex = argv[1];

            printf("The regular expression provided is: %s\n", regex);

            ////////////////////////////////////
            // Goal here: to cycle through the file and store each line of input as a string
            while( fgets(buff,200,fp) != NULL)
            {
                readLine[i] = buff;
                printf("Reading: %s", readLine[i]);
                i++;
            }
            /*
            while( i>0)
            {
                printf("[%d]: %s",i,readLine[i]);
                i--;
            }
*/
            //fgets(readLine, 200, fp);   // Assuming max of 200 charachters per line.

            fclose(fp);
        }
    }
    else
    {
        RETRN_CODE = 1;
        printf("[Error] Invalid format!\n... Usage <programname> <regex> <filename>\n");
    }
    exit(RETRN_CODE);
}
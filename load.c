//
// Created by anita on 2/21/18.
//
#include <stdio.h>

void doTest(char ** stringArg)
{
    int i = 0;

    while (stringArg[i] != NULL)
    {
        printf("hey I work: %s", stringArg[i]);
        i++;
    }
}

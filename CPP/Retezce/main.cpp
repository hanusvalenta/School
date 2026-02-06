#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_DELKA 100

int main() {
    int pocet;

    printf("Zadejte pocet retezcu: ");
    if (scanf("%d", &pocet) != 1 || pocet <= 0) {
        printf("Neplatny vstup.\n");
        return 1;
    }

    while (getchar() != '\n');

    char **seznam = (char **)malloc(pocet * sizeof(char *));
    if (seznam == NULL) {
        return 1;
    }

    for (int i = 0; i < pocet; i++) {
        char buffer[MAX_DELKA];
        printf("Zadejte %d. retezec: ", i + 1);
        
        if (fgets(buffer, MAX_DELKA, stdin)) {
            buffer[strcspn(buffer, "\n")] = 0;
            
            seznam[i] = (char *)malloc((strlen(buffer) + 1) * sizeof(char));
            if (seznam[i] != NULL) {
                strcpy(seznam[i], buffer);
            }
        }
    }

    printf("\nUlozene retezce:\n");
    for (int i = 0; i < pocet; i++) {
        printf("%d: %s\n", i + 1, seznam[i]);
    }

    for (int i = 0; i < pocet; i++) {
        free(seznam[i]);
    }
    free(seznam);

    return 0;
}
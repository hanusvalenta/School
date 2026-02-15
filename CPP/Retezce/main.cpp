#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_DELKA 100

int main() {
    int pocet;

    printf("Kolik zaznamu?\n");
    if (scanf("%d", &pocet) != 1 || pocet <= 0) {
        printf("Neplatne cislo :c\n");
        return 1;
    }

    while (getchar() != '\n');

    char **seznam = (char **)malloc(pocet * sizeof(char *));
    if (seznam == NULL) {
        return 1;
    }

    printf("Rezervovano misto pro %d retezcu\n", pocet);

    for (int i = 0; i < pocet; i++) {
        char buffer[MAX_DELKA];
        printf("Napis obsah %d. retezce: \n", i + 1);
        
        if (fgets(buffer, MAX_DELKA, stdin)) {
            buffer[strcspn(buffer, "\n")] = 0;
            
            seznam[i] = (char *)malloc((strlen(buffer) + 1) * sizeof(char));
            if (seznam[i] != NULL) {
                strcpy(seznam[i], buffer);
            }
        }
    }

    for (int i = 0; i < pocet; i++) {
        printf("ID: %d text:%s delka retezce - %zu\n", i, seznam[i], strlen(seznam[i]));
    }

    int id_oprava;
    while (1) {
        printf("Ktery zaznam opravit? Cislo zaznamu: \n");
        if (scanf("%d", &id_oprava) == 1 && id_oprava >= 0 && id_oprava < pocet) {
            break;
        } else {
            printf("Neplatne id >:c \n");
            while (getchar() != '\n');
        }
    }

    printf("Vybrano: %s\n", seznam[id_oprava]);
    
    int index_znaku;
    printf("Zadej poradi znamu - od indexu 1: \n");
    scanf("%d", &index_znaku);
    
    char novy_znak;
    printf("Zadej znak - \n");
    while (getchar() != '\n');
    scanf("%c", &novy_znak);

    if (index_znaku > 0 && index_znaku <= (int)strlen(seznam[id_oprava])) {
        seznam[id_oprava][index_znaku - 1] = novy_znak;
    }

    printf("Novy seznam: \n");
    for (int i = 0; i < pocet; i++) {
        printf("id: %d text:%s delka retezce - %zu\n", i, seznam[i], strlen(seznam[i]));
    }

    printf("Zav\n");

    for (int i = 0; i < pocet; i++) {
        free(seznam[i]);
    }
    free(seznam);

    return 0;
}
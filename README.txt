COMANDO CRIATIVO -------------------------------------------------------------------------------------------------------

Nome: GET_CITIES_BY_REGION <regiao> <pais>

Descrição: Este comando retorna todas as cidades de uma determinada região de um país. O comando chama-se através
da frase GET_CITIES_BY_REGION, e os argumentos são a região e o país. A região é um número inteiro e o país
é uma string.

Exemplo:
GET_CITIES_BY_REGION 14 Portugal

Portugal -> a dos cunhados
Portugal -> alcabideche
Portugal -> alcoentre
Portugal -> alenquer
Portugal -> alges
Portugal -> alhandra
Portugal -> almargem do bispo
Portugal -> amadora
Portugal -> apelacao
Portugal -> arruda dos vinhos
Portugal -> aveiras de cima
Portugal -> azambuja
Portugal -> barcarena
Portugal -> belas
Portugal -> bobadela
Portugal -> bucelas
Portugal -> camarate
Portugal -> canecas
Portugal -> carcavelos
Portugal -> carnaxide
Portugal -> carregado
Portugal -> cascais
Portugal -> castanheira do ribatejo
Portugal -> colares
Portugal -> ericeira
Portugal -> estoril
Portugal -> famoes
Portugal -> linda-a-velha
Portugal -> lisbon
Portugal -> loures
Portugal -> lourinha
Portugal -> mafra
Portugal -> malveira
Portugal -> milharado
Portugal -> moscavide
Portugal -> odivelas
Portugal -> paco de arcos
Portugal -> parede
Portugal -> pero pinheiro
Portugal -> pontinha
Portugal -> porto salvo
Portugal -> povoa de santa iria
Portugal -> povoa de santo adriao
Portugal -> queijas
Portugal -> queluz
Portugal -> ramada
Portugal -> rio de mouro
Portugal -> sacavem
Portugal -> santa iria de azoia
Portugal -> santo antao do tojal
Portugal -> sao domingos de rana
Portugal -> sao joao da talha
Portugal -> sao joao dos montes
Portugal -> sao pedro da cadeira
Portugal -> silveira
Portugal -> sintra
Portugal -> sobralinho
Portugal -> terrugem
Portugal -> torres vedras
Portugal -> unhos
Portugal -> venda do pinheiro
Portugal -> ventosa
Portugal -> vialonga
Portugal -> vila franca de xira

------------------------------------------------------------------------------------------------------------------------


UTILIZAÇÃO DO CHATGPT --------------------------------------------------------------------------------------------------
No nosso caso, optámos por recorrer ao GitHub Copilot, uma ferramenta de inteligência artificial mais direcionada
para a programação. Esta ferramenta foi utilizada essencialmente para a descoberta da sintaxe correta a aplicar
para determinadas estruturas. A aplicação dos iteradores e a utilização dos SubSets são dois exemplos de situações
em que a inteligência artificial ajudou bastante a acelerar o processo de desenvolvimento e de aprendizagem.


Caso específico:

Prompt
Can you generate me a function that applies the haversine distance formula? It takes as arguments the startLat,
the startLong, the endLat and the endLong, and returns a double with the distance in kilometers

Resposta
Sure, here is a Java function that applies the Haversine distance formula:

public static double haversineDistance(double startLat, double startLong, double endLat, double endLong) {
    final int EARTH_RADIUS = 6371; // Earth radius in kilometers

    double dLat = Math.toRadians(endLat - startLat);
    double dLong = Math.toRadians(endLong - startLong);

    startLat = Math.toRadians(startLat);
    endLat = Math.toRadians(endLat);

    double a = Math.pow(Math.sin(dLat / 2), 2)
             + Math.cos(startLat) * Math.cos(endLat)
             * Math.pow(Math.sin(dLong / 2),2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return EARTH_RADIUS * c; // distance in kilometers
}

This function calculates the Haversine distance between two points on the Earth's surface specified by their latitudes
and longitudes in degrees. The result is the distance in kilometers.

Utilização da solução
A solução apresentada foi utilizada para calcular a distância entre duas cidades, recorrendo às suas coordenadas. Esta
função foi, como esperado, utilizada nas funções getCitiesAtDistance e getCitiesAtDistance2, que implementam os
comandos GET_CITIES_AT_DISTANCE e GET_CITIES_AT_DISTANCE2, respetivamente. Optámos por pedir a função ao Copilot
devido à complexidade da expressão matemática e à necessidade de implementar a fórmula de forma 100% correta,
evitando assim erros e um gasto de tempo desnecessário.

------------------------------------------------------------------------------------------------------------------------


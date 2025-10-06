---
marp: true
title: KlicHodnota
author: Valenta, Zahradník, Novotný
size: 16:9
theme: default
paginate: faalse
header: 'V korenspondenční volbě ANO vyhrálo pouze v Rusku & Bělorusku' 
footer: '© 2025 Valenta, Zahradník, Novotný a Femboy catboy L(s)igma Ltd. Corp.'
style: |
  /* Custom CSS can go here */
  .columns {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 1rem;
  }
---

## Prezentace: NoSQL databáze typu Key-Value

---

### Vlastnosti Key-Value databází
- Vysoce efektivní pro čtení a zápis
- Snadné přidávání nových klíčů a hodnot bez nutnosti změny schématu
- Lze snadno škálovat podle potřeby
- Ukládání dat ve stylu Klíč-Hodnota

---

### Výhody a nevýhody
| Výhody| Nevýhody|
|-:|:-|
| Vysoká rychlost| Méně strukturální integrita|
| Snadná škálovatelnost| Omezené dotazovací možnosti|
| Flexibilní schéma| Někdy složitější správa dat|

---

### Použití Key-Value databází
- **Caching**: Rychlý přístup k často používaným datům
- **Ukládání konfigurací**: Dynamické konfigurace aplikací

---

# Zástupci
- etcd
- Redis

--- 

## etcd
- 2019
- Open Source
- .NET, Rust, Java(Script), C/C++, PHP, Go, a další
- Kubernetes
- Cloud Foundry

---

## etcd - Syntax

```sh
etcdctl put key_name value # Create/Update
etcdctl get key_name # Select
etcdctl del key_name # Delete
```

\*https://db-engines.com/en/system/etcd

---

# Redis
- 2009
- Často porovnávané s Apache Spark (SQL), Rockset and atoti.
- Open Source
- C
- Ukládání dat v paměti pro extrémní rychlost
- Podpora různých datových struktur (nejen klíč-hodnota)

\*https://db-engines.com/en/system/Redis

---

## Redis - Syntax

```sh
redis-cli SET key_name "some value" # Create/Update
redis-cli GET key_name              # Select
redis-cli DEL key_name              # Delete
```

---
# Amazon DynamoDB
- 2012
- Často porovnávané s MongoDB, Cassandra, Google Bigtable
- Cloudová služba od AWS
- Interně napsaná v Java + C++
- Fully managed NoSQL databáze (key-value + dokumentová)
- Škálovatelná, vysoká dostupnost, podpora sekundárních indexů

*https://db-engines.com/en/system/Amazon+DynamoDB
*https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/

---
## Amazon DynamoDB - Syntax (AWS CLI)
``` # Create table (s jedním primárním klíčem - partition key)
aws dynamodb create-table \
    --table-name MyTable \
    --attribute-definitions AttributeName=key_name,AttributeType=S \
    --key-schema AttributeName=key_name,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

# Insert (PutItem)
aws dynamodb put-item \
    --table-name MyTable \
    --item '{"key_name": {"S": "some_key"}, "value": {"S": "some value"}}'

# Get item
aws dynamodb get-item \
    --table-name MyTable \
    --key '{"key_name": {"S": "some_key"}}'

# Delete item
aws dynamodb delete-item \
    --table-name MyTable \
    --key '{"key_name": {"S": "some_key"}}'
```
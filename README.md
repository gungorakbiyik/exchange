# exchange
GET http://localhost:9090/rate?base=USD&target=TRY

POST http://localhost:9090/rate
{
    "baseCurrency": "USD",
    "targetCurrency": "TRY"
}

GET http://localhost:9090/conversion?base=USD&target=TRY&amount=26

POST http://localhost:9090/conversion
{
    "baseCurrency": "USD",
    "targetCurrency": "TRY"    ,
    "amount": 30
}

POST http://localhost:9090/conversion-list
{
    "transactionId": null,
    "startDate": "2010-01-01T00:00:00.000",
    "endDate": "2050-01-01T00:00:00.000",
    "page": {
            "page": 0,
            "size": 5
        }
}

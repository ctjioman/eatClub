# eatClub

Assumptions:
- Deals have either Open AND Close or Start AND End
- When deals have not start time or end time, then the deal is active all the time while the resturant is open

# How to Run:
1. on root folder
2. run `mvn spring-boot:run`
3. run curl commands:
4. for Task 1:
    a. run `curl http://localhost:8080\?timeOfDay=<timeOfDeal>`
        ai. Where <timeOfDeal> is in the format of h:mma. Hours : Minutes am/pm (without the spaces)
    b. eg: `curl http://localhost:8080\?timeOfDay=10:31am`
5. For Task 2:
    a run `curl http://localhost:8080\/getPeakTimeDeals`
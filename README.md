# Hair Salon Management App

#### February 26th, 2016

#### By Chris Young

## Description

A hair salon app written in Java that assists owners in managing stylist/client relationships. Gives the user the ability to add a list of the stylists, and for each stylist, add clients who see that stylist. The stylists work independently, so each client only belongs to a single stylist. Utilizes a SQL database to store stylists and their clients.

## Setup

### In PSQL:
* CREATE DATABASE hair_salon;
* CREATE TABLE stylists (id serial PRIMARY KEY, stylist varchar);
* CREATE TABLE clients (id serial PRIMARY KEY, client varchar, stylist_id int);

* If you also want to run the tests I have written, run: CREATE DATABASE to_do_test WITH TEMPLATE to_do;

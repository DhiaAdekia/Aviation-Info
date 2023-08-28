<template>
  <div class="hello">
    <div class="container">
      <h2 class="form-title">Airport Information</h2>

      <div class="form-group">
        <label for="airportCodeInput">Airport Code:</label>
        <input type="text" id="airportCodeInput" v-model="AirportCode" />
      </div>

      <div class="form-group">
        <label for="dateInput">Date:</label>
        <input id="dateInput" type="date" v-model="date" />
      </div>

      <button @click="getInfo">Get info</button>

      <div v-if="data">
        <template v-if="isEmptyData(data) || data === null">
          <p>no Airport information</p>
        </template>
        <template v-else>
          <h4>Number Of Departing Flights: {{ data.numberOfDepartingFlights.length }} flights</h4>
          <h4>Number Of Arriving Flights: {{ data.numberOfArrivingFlights.length }} flights</h4>
          <h4>Departing Baggage Total Pieces: {{ data.DepartingBaggageTotalPieces }} Kg</h4>
          <h4>Arriving Baggage Total Pieces: {{ data.ArrivingBaggageTotalPieces }} Kg</h4>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AirportComponent',
  data: function() {
    return {
      AirportCode: null,
      date: null,
      data: null
    };
  },
  methods: {
    getInfo: function() {
      this.data = null;
      return axios
        .get(`http://localhost:8080/api/Airport?iataCode=${this.AirportCode}&date=${this.date}`)
        .then(response => {
          this.data = response.data;
          console.log(response);
        })
        .catch(error => {
          console.log(error);
        });
    },
    isEmptyData(data) {
      return (
        data.numberOfDepartingFlights.length === 0 &&
        data.numberOfArrivingFlights.length === 0 &&
        data.DepartingBaggageTotalPieces === 0 &&
        data.ArrivingBaggageTotalPieces === 0
      );
    }
  }
};
</script>

<style scoped>
</style>
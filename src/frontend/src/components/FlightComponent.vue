<template>
  <div class="hello">
    <div class="container">
      <h2 class="form-title">Flight Information</h2>

      <div class="form-group">
        <label for="flightNumberInput">Flight Number:</label>
        <input type="text" id="flightNumberInput" v-model="FlightNumber" />
      </div>

      <div class="form-group">
        <label for="dateInput">Date:</label>
        <input id="dateInput" type="date" v-model="date" />
      </div>

      <button @click="getInfo">Get info</button>

        <div>
          <template v-if="data === ''">
            <p>no Flight information</p>
          </template>
          <template v-else-if="data !== null">
              <h4>Baggage Weight: {{ data.baggageWeight }} Kg</h4>
              <h4>Cargo Weight: {{ data.cargoWeight }} Kg</h4>
              <h4>Total Weight: {{ data.totalWeight }} Kg</h4>
          </template>
        </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'FlightComponent',
  data: function() {
    return {
      FlightNumber: null,
      date: null,
      data: null
    };
  },
  methods: {
    getInfo: function() {
      this.data = '';
      return axios
        .get(`http://localhost:8080/api/flight?flightNumber=${this.FlightNumber}&date=${this.date}`)
        .then(response => {
          this.data = response.data;
          console.log(response);
          console.log(this.data);
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>

<style scoped>




</style>

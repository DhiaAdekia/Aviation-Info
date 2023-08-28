<template>
  <div id="app">
    <img alt="Smart4Aviation logo" src="./assets/logo2.png" class="logo">
    <div class="components">
      <FlightComponent class="component" />
      <AirportComponent class="component" />
    </div>
  </div>
</template>

<script>
import FlightComponent from './components/FlightComponent.vue'
import AirportComponent from './components/AirportComponent.vue'

export default {
  name: 'App',
  components: {
    AirportComponent,
    FlightComponent
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.logo {
  margin-bottom: 20px;
}

.components {
  display: flex;
  justify-content: center;
  gap: 30px; /* Adjust spacing between components */
}

.component {
  background-color: #f0f0f0; /* Example background color */
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  flex: 1;
  max-width: 400px; /* Set a maximum width for the components */
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

@media (max-width: 768px) {
  /* Change the breakpoint value to your desired screen width */
  .components {
    flex-direction: column;
    align-items: center;
  }
}

.form-group input {
  flex: 1;
}

button {
  margin-top: 10px;
}


.form-title {
  font-size: 24px;
  margin-bottom: 20px;
}

.form-group {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.form-group label {
  width: 140px;
  margin-right: 10px;
}

/* Add media query for smaller screens if needed */
@media (max-width: 600px) {
  .form-group {
    flex-direction: column;
    align-items: flex-start;
  }

  .form-group label {
    width: 100%;
    margin-bottom: 5px;
  }

  .form-group input[type="date"] {
    width: 100%;
  }
}

</style>

import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import Login from "./pages/Login";
import CustomerPage from "./pages/CustomerPage";

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/signup" component={CustomerPage} />
        <Route path="/login" component={Login} />
        <Route exact path="/">
          <Redirect to="/login" />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;

import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { Home } from './pages';

const routes = [
  {
    path: '/',
    component: Home,
  },
];

export function App() {
  return (
    <Router>
      <div>
        <Switch>
          {routes.map((route) => (
            <Route exact key={route.path} path={route.path}>
              <route.component />
            </Route>
          ))}
        </Switch>
      </div>
    </Router>
  );
}

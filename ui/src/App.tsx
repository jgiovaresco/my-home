import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { Home } from './pages';
import { Header } from './core/components';

const routes = [
  {
    path: '/',
    label: 'Home',
    component: Home,
  },
];

export function App() {
  const navItems = routes.map(({ path, label }) => ({ path, label }));

  return (
    <Router>
      <Header navItems={navItems} />

      <Switch>
        {routes.map(({ path, component: Page }) => (
          <Route exact key={path} path={path} render={() => <Page />} />
        ))}
      </Switch>
    </Router>
  );
}

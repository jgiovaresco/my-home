import React from 'react';
import { Provider } from 'react-redux';
import { Switch, Route } from 'react-router-dom';
import { ConnectedRouter } from 'connected-react-router';

import { Header } from './core/components';
import { store, history } from './store';
import { routes } from './router';

export function App() {
  const navItems = routes.map(({ path, label }) => ({ path, label }));

  return (
    <Provider store={store}>
      <ConnectedRouter history={history}>
        <Header navItems={navItems} />

        <Switch>
          {routes.map(({ path, component: Page }) => (
            <Route exact key={path} path={path} render={() => <Page />} />
          ))}
          <Route render={() => <div>Page not found!</div>} />
        </Switch>
      </ConnectedRouter>
    </Provider>
  );
}

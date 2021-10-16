import React from 'react';
import { Provider } from 'react-redux';
import { Route, Switch } from 'react-router-dom';
import { ConnectedRouter } from 'connected-react-router';
import { history, store } from './_store';
import { routes } from './_router';
import { Auth0Header } from './auth';

export function App() {
  return (
    <Provider store={store}>
      <ConnectedRouter history={history}>
        <Auth0Header />

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

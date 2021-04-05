import { configureStore, ThunkAction, Action } from '@reduxjs/toolkit';
import { createBrowserHistory } from 'history';
import { routerMiddleware } from 'connected-react-router';

import { rootReducer } from './root-reducer';

export const history = createBrowserHistory();
export const store = configureStore({
  reducer: rootReducer(history),
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().prepend(routerMiddleware(history)),
});

// export const store = createStore(rootReducer(history), initialState, enhancer);

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export type AppThunk<ReturnType = void> = ThunkAction<
  ReturnType,
  RootState,
  unknown,
  Action<string>
>;

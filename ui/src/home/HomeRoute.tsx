import React from 'react';
import './HomeRoute.scss';
import Logo from './house.svg';

export function HomeRoute() {
  return (
    <div className="App">
      <Logo className="logo" />
      <p>Welcome to MyHome!</p>
    </div>
  );
}

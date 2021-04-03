import React from 'react';
import { NavLink } from 'react-router-dom';

import './navigation.scss';

export interface NavItem {
  path: string;
  label: string;
  active?: boolean;
}

export interface NavigationProps {
  navItems?: NavItem[];
}

export const Navigation: React.FC<NavigationProps> = ({ navItems = [] }) => {
  const items = navItems.map((item) => (
    <li key={item.path}>
      <NavLink exact to={item.path} activeClassName="active">
        {item.label}
      </NavLink>
    </li>
  ));

  return (
    <nav className="navigation">
      <ul>{items}</ul>
    </nav>
  );
};
